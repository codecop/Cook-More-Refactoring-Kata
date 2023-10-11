# Cook More - Splitting a Large Class Exercise

Exercise to split a large "interface" (a public API) following SRP and ISP and updating
tests and clients while doing that.

## Assignment

* Split the class `CookingService` into smaller classes. (Hint: Look for SRP and ISP violations.)
* Update the `CookingServiceTest` and `Client` accordingly.
* The `CookingService`'s API must stay the same for legacy code (which is not included). (Hint: Use Facade design pattern.)

## Notes for Facilitators

* The assignment takes roughly two hours including break to complete.
* It helps to remind people about SRP and ISP up front.
* Keep reminding people that the focus is on splitting `CookingService`. There are many things that could be improved, e.g. test names, packages, refactoring the client itself etc. While all these changes are valid, focus of this exercise is splitting the large class.
* A detailed list of hints about responsibilities and roles is in `trainer_notes_v2.txt` in repository.

### License

[New BSD License](http://opensource.org/licenses/bsd-license.php), see `license.txt` in repository.
