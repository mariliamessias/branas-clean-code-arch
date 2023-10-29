import Passenger from "../../domain/Passenger";

test("Dont should create a passenger", function(){
    expect(() => new Passenger("", "","", "").toThrow(new Error("Invalid Name")))
})

test("Dont should create a passenger with invalid email", function(){
    expect(() => new Passenger("", "Jon Foe","", "").toThrow(new Error("Invalid Email")))
})