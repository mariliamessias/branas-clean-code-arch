import Driver from "../../domain/Driver";

test("Dont should create a driver", function(){
    expect(() => new Driver("", "","", "", "").toThrow(new Error("Invalid Name")))
})

test("Dont should create a driver with invalid email", function(){
    expect(() => new Driver("", "Jon Doe","", "", "").toThrow(new Error("Invalid Email")))
})