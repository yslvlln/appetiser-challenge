# appetiser-challenge
 
 ### General Requirements
 1. The list with the **Track** **Name**, **Artwork**, **Price** and, **Genre**
     - You can find solution here through these views `ListFragment.kt` and `RecommendedFragment.kt`
     - I didn't create another detail view but instead added a redirection to a web browser which loads the overview of the movie. Also, the `shortDescription` and `longDescription` was also utilized in the list items.

 2. _...have a placeholder image if for some reason the URL is unable to be loaded. Images must not be duplicated when scrolling._
     - This was accomplished by creating another view that displays the **initials** of the movie name and its **artist**. The background color of the view was also randomized to reduce the probability of duplication.

3. Persistence
     - I used room database for the persistence. This is updated every successful api call.

4. Architecture
     - I make use of MVVM architecture because of the ff:
         - Currently using MVVM on my current telco project.
         - Most common architecture in the past few months.
         - Pros: We can easilty divide the project by its concern (ex. business logic, view logic, etc.)
         - Cons: As for the making of this project which is pretty small, I think the MVVM is too much for such a small scale project. But we we're to scale up some legacy applications then MVVM is a good choice.

###### Firm believer that software developers are forever students!

##### PS.
_Whatever the result will be, thank you for giving me the chance to showcase my skillset. Also, for giving me the time and consideration for the role._
