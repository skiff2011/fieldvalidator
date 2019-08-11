# FieldValidator
**_Validate fields, not views_**

Every Android developer faces with validating user input (email, phone, license agreement etc.). 
Validating user input means getting this input from View, validating it and then displaying the validation result.
Screen validation with more than several Edittexts could be a bit challenging, 
combining everything listed above for every single view, could be a bit challenging and brings a lot of boilerplate code.
FieldValidator is attempt to get rid of coupling user input validation with view and focus mainly on data validation 
without boilerplate. 
Although such approach could be useful for MVVM like architecture with Android Databinding usage, 
it can be used in other architecture patterns and without DataBinding. 
The library is designed to be used along with Kotlin.

## Quick overview
- Supports simple on demand validation
- Auto validation, instant validation as field value changes
- Validation result is delievering directly to views
- Easy API and Kotlin delegates to simplify initialisation

## Installation ##
Step 1: Add in your root `build.gradle` of your project
```Groovy
allprojects {
    repositories {
      // ...
      maven { url 'https://jitpack.io' }
    }
  }
```
Step 2: Add the dependency to your app gradle file
```Groovy
dependencies {
  //...
  implementation 'com.github.auxility:baseadapter:$latestVersion'
  //...
}
```
## Usage ##
1. ```Condition<T : Any?>``` class instance is responsible for single field validation. 
You have to choose condition that suits your nedds or write your own.
```Kotlin
open class RegexCondition(
  private val error: String,
  private val regex: Regex
) : Condition<String> {
  override fun validate(value: String): ValidationResult =
    if (value.matches(regex)) Valid else Error(error)
}
```
2. ```ValidationViewState<T : View>``` is responsible for displaying View`s state depending on validation result. 
Validation error is represnted by ```String``` value. Implement this interface for required view.
```Kotlin
class TextInputLayoutViewState : ValidationViewState<TextInputLayout> {
  override fun showError(error: String, view: TextInputLayout) {
    view.error = error
  }

  override fun hideError(view: TextInputLayout) {
    view.error = null
  }
}
```
3. Create validator instance in your ViewModel(Presenter, Activity, Fragment etc.)
```Kotlin
class YourViewModel() {
   // ...
  val validator = FieldValidator()
   // ...
}
```
4. Define your data fields using corresponding delegate provided by library. 
ViewId is a key for field storing in a map. 
If you don`t want to display validation state, you can pass any unique ```Int``` Id. 
```Kotlin
class YourViewModel() {
// ...
var string0: String by validator.validateable(
    initialValue = "",
    viewId = R.id.til1,
    condition = EmptyStringCondition(),
    viewState = TextInputLayoutViewState()
  ) { value ->
    // value changed listener if required (optional)
  }
  // ...
}
```
5. Subscribe view to validation state changes with a help of ```@BindingAdapter``` method provided by the library 
by passing validator instance as argument. 
If view state display is not required omit this step (Silent validation).
```XML
<?xml version="1.0" encoding="utf-8"?>
<layout>
  // ...
  <com.google.android.material.textfield.TextInputLayout
        android:id="@+id/til1"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:hint="Some Hint"
        app:errorEnabled="true"
        validator="@{viewModel.validator}"
        >

      <com.google.android.material.textfield.TextInputEditText
          android:layout_width="match_parent"
          android:layout_height="wrap_content"
          android:text="@={viewModel.string0}"
          />

    </com.google.android.material.textfield.TextInputLayout>
    // ...
</layout>
```
As you can see two way binding is used to update field value, but it is optional. 
You can use any suitable way for you, TextWatcher for example.

Alternatively:
```Kotlin
import com.skiff2011.fieldvalidator.view.bindValidator

class YourFragment {
  // ...
  val validator = FieldValidator()
  lateinit var til1: TextInputLayout
  // ...
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    // create view
    bindValidator(til1, validator)
  }
  
  // ...
}
```
6. Validate validator by calling ```validator.validate```.
The returned ```Boolean``` value will be the result. You can also pass ```List<Int>``` of ids to validate only some fields.
```Kotlin
class YourViewModel() {
// ...
  val validator = FieldValidator()
  // ...
  
  fun onValidateButtonClicked() {
    if (validator.validate) {
      // go next
    }
  }
}
```

You can also use ```FieldAutoValidator``` to perform field validation as its value changes.
You have to create ```FieldAutoValidator``` instance instead of ```FieldValidator``` 
as desriben above and pass ```ValidationCallback``` instance, which will be called when validator changes its state.
```Kotlin
class YourViewModel() {
  // ...
  val validator = FieldAutoValidator(validationCallback = ValidationCallback { value ->
    //validator new status
    if (!value) {
    //disable button
    }
  })
  // ...
}
```
Then you have to enable auto validation by calling ```validator.enableAutoValidation()```. 
You can pass ```List<Int>``` of ids to enable validation only for some views.
```Kotlin
class YourViewModel() {
  // ...
  val validator
  var buttonClickCounter = 0
  // ...
  fun onButtonClicked() {
  //enable autovalidation after the first button click
    if (counter == 0) {
      validator.enableAutoValidation()
    }
  }
}
```
Sometime it is necessary to include/exclude fields from validation process depending on some conditions.
For this purpose you can use ```validator.enableAutoValidation(listOf(R.id.til1, R.id.til2)``` 
and 
```validator.disableAutoValidation(listOf(R.id.til1, R.id.til2)```
After disabling field autovalidation, View tied to field will return in default state(Valid).
