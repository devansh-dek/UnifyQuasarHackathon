from flask import Flask, request, jsonify, render_template
from flask_cors import cross_origin
import cv2
import pickle
import face_recognition
import numpy as np

app = Flask(__name__)

# def preprocess(data):
#     """Preprocess the input image data for face recognition"""
#     img_arr = cv2.imdecode(data, cv2.IMREAD_COLOR)
#     img_rgb = cv2.cvtColor(img_arr, cv2.COLOR_BGR2RGB)
#     return img_rgb, "cnn"

def preprocess(data):
    """Preprocess the input image data for face recognition"""
    # Convert bytes to numpy array
    nparr = np.frombuffer(data, np.uint8)
    # Decode image data
    img_arr = cv2.imdecode(nparr, cv2.IMREAD_COLOR)
    img_rgb = cv2.cvtColor(img_arr, cv2.COLOR_BGR2RGB)
    return img_rgb, "cnn"


def predict(img_rgb, detection_method):
    """Perform face recognition on the preprocessed image"""
    boxes = face_recognition.face_locations(img_rgb, model=detection_method)
    encodings = face_recognition.face_encodings(img_rgb, boxes)
    return encodings, boxes

def postprocess(encodings, boxes, image):
    """Postprocess the face encodings for recognition and draw annotations on the image"""
    # Load the known faces and embeddings
    encodings_path = "encodings"
    data = pickle.loads(open(encodings_path, "rb").read())

    # Perform face recognition
    names = []
    for encoding in encodings:
        matches = face_recognition.compare_faces(data["encodings"], encoding)
        if True in matches:
            matched_idxs = [i for i, b in enumerate(matches) if b]
            counts = {}
            for i in matched_idxs:
                name = data["names"][i]
                counts[name] = counts.get(name, 0) + 1
            name = max(counts, key=counts.get)
            if counts.get(name) > 18:
                names.append(name)
            else:
                names.append("Unknown")
        else:
            names.append("Unknown")

    # Draw rectangles and names on the image
    annotated_image = image.copy()
    for ((top, right, bottom, left), name) in zip(boxes, names):
        cv2.rectangle(annotated_image, (left, top), (right, bottom), (0, 255, 0), 2)
        y = top - 15 if top - 15 > 15 else top + 15
        cv2.putText(annotated_image, name, (left, y), cv2.FONT_HERSHEY_SIMPLEX, 0.75, (0, 255, 0), 2)

    return names, annotated_image

@app.route('/')
def index():
    return render_template('index.html')

import base64

@app.route('/predict', methods=['POST'])
@cross_origin(origin='*', methods=['POST'], allow_headers=['Content-Type'])
def handle_request():
    """Handle POST requests containing image data"""
    # Get image data from the request
    file = request.files['image']
    img_data = file.read()
    
    # Preprocess image
    img_rgb, detection_method = preprocess(img_data)
    
    # Predict
    encodings, boxes = predict(img_rgb, detection_method)
    
    # Postprocess
    names, annotated_image = postprocess(encodings, boxes, img_rgb)
    
    # Encode image as base64
    _, buffer = cv2.imencode('.jpg', annotated_image)
    encoded_image = base64.b64encode(buffer).decode('utf-8')
    
    #save image as base64 file
    with open('annotated_image.jpg', 'wb') as f:
        f.write(base64.b64decode(encoded_image))
    
    return jsonify({"names": names, "image": encoded_image})

    

if __name__ == '__main__':
    app.run(debug=True)
