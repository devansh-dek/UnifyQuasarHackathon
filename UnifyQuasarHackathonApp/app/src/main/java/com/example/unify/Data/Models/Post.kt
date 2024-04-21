package com.example.unify.Data.Models

class Post {
    var postUrl : String = ""
    var caption : String  = ""
    var time : String = ""
    var uid : String = ""
    constructor()
    constructor(postUrl: String, caption: String) {
        this.postUrl = postUrl
        this.caption = caption
    }

    constructor(postUrl: String, caption: String, time: String, name: String) {
        this.postUrl = postUrl
        this.caption = caption
        this.time = time
        this.uid = name
    }

}
