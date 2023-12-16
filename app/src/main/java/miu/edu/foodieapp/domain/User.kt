package miu.edu.foodieapp.domain

class User constructor(firstname: String, lastname: String,username: String, password: String, email: String, phone: String) {
    var fname: String = firstname
    val lname: String = lastname
    var name: String = username
    val pass: String = password
    val email: String = email
    val phone: String = phone
}