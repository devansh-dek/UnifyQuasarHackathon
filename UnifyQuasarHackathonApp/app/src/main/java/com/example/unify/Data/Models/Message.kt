package com.example.unify.Data.Models

class Message {
    var message : String? = null
    var sendername : String? = null
    var senderId: String? = null
    constructor()
    constructor(message: String?, senderId: String?) {
        this.message = message
        this.senderId = senderId
    }

    constructor(message: String?, sendername: String?, senderId: String?) {
        this.message = message
        this.sendername = sendername
        this.senderId = senderId
    }


}