Student name: Cecchinato Federico
Student number: 200143109

Now complete the statements below for each level you wish to be marked. Replace all text in square brackets.

LEVEL ONE

My code demonstrates inheritance in the following way...

I have a superclass called [Account]

This superclass is extended into at least two subclasses called [User, Admin]

For each of the named subclasses complete the following...

Subclass 1.

Subclass [User] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties are [myAssets, balance]

These/this new properties/property are used by the subclass in the following way: [myAssets is used to store the assets bought by the user (lines: 34, 46, 48, 51, 63, 69, 72, 74). Balance is used to store the amount of money the user has (lines: 32, 35, 49, 68)]

Subclass [User] extends the superclass by overriding the following methods (there must be at least one): [save (lines: 25-28), print (lines: 66-88)]

These overridden methods are used in the working code in the following places: [Menu.java (lines: 58 and 85)]

Subclass 2.

Subclass [Admin] extends the superclass by adding at least one property and its getters and setters. The name(s) of the added properties are [KEY]

These/this new properties/property are used by the subclass in the following way: [they key is used to give permission to another user to create an Admin account (line: 42))]

Subclass [Admin] extends the superclass by overriding the following methods (there must be at least one): [save (lines: 33-35) print (lines: 39-44)]

These overridden methods are used in the working code in the following places: [Menu.java (lines: 58 and 85)]


LEVEL TWO

Polymorphism consists of the use of the Substitution principle and Late Dynamic binding.

In my code, polymorphism is implemented in at least two places…

Example 1.

The substitution principle can be seen in use in [Main (line: 21)]. The name of the superclass used in this example is [Account] and the subclasses used are [User, Admin].

!(IMPORTANT)!
The method authenticate is calling 2 other methods: SignIn and SignUp. SignIn is calling the static method retrieve in the class Account that could either return a User or an Admin depending on the file extension. In the same way SignUp can return an instance of User or an instance of Admin. For this reason, when all methods have been resolved the final result is the same as: Account account = new User(...) or Account account = new Admin(...).

Late dynamic binding can be seen in [Menu (line: 85)].

[Both admin and user can be printed but they need to add their own implementation (because of the different proprieties they have), for this reason, polymorphism is necessary. So when calling account.print() based on the instance of the class two different behaviours are shown]

Example 2.

The substitution principle can be seen in use in [Main (lines: 9-18)]. The name of the superclass used in this example is [Asset] and the subclasses used are [Crypto, Stock].

Late dynamic binding can be seen in [Market (line: 63)].

[Both stock and crypto have a variate method used to change the price with a variability variable (which is set depending if the object is an instance of stock or crypto) because of the higher volatility of crypto currencies. They both share proprieties such as ticker company price and prices history, and for this reason they are both subclasses of asset. Polymorphism is necessary to variate the price differently based on the instance of the class]
