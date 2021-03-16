1\. Introduction
----------------

### 1.1 Purpose

Countless hours can be spent trying to pick movies and shows that both you and your partner are interested in watching. Scrolling through movie titles and arguing over who gets the final say isn't the ideal way to spend a movie night. Cuddlebug offers a seamless way to choose the best titles for you and your partner, or even your friends!

### 1.2 Scope

-   Have a swiping functionality for users to swipe through movies (liking and disliking them)

-   Have a MyList page that allows users to review their past liked movies

-   Have a friends feature where users can add friends who use the app and compare movie lists

-   Create an all in one platform for browsing movies efficiently displaying all relevant information (Imdb score, description, title, year of release etc.)

-   Written in Java anticipating release on Android OS

### 1.3 Definitions

MyList: Page dedicated to listing liked movies specific to each user account

FriendList: Page dedicated to listing users connected through the application

ExploreMovies: Page dedicated to swiping through movie posters (liking and disliking movies)

CardStack: Visual stacked image representation of movies displayed on main page of application

### 1.4 References

Yuyakaido. "Yuyakaido/CardStackView." GitHub, github.com/yuyakaido/CardStackView.

### 1.5 Overview

CuddleBug is a fun, attractive alternative to movie selection. We aim to acquire an up to date list of relevant films/tv shows available in Canada, design a modern, intuitive GUI for selection and have a user account system in place that permits sharing data between two or more accounts.

This process will help reduce time wasted trying to browse movies/tv shows a user might want to watch at any given time. Instead of taking the time to browse for a title before watching, CuddleBug users will have access to a pre-compiled list of all the movies they want to watch, alongside the movies their friends or partner may want to watch with them.

2\. Functionality
-----------------

### 2.1 App Perspective

![](https://lh4.googleusercontent.com/10Hz_p_Hx1uqEc-RuHF-oSsgzgU_DbyRj9pw8fF6YK0KUaTpnQitNa2EuUvaV-EPO-d-Yvs5bquY0TCBYyvxnozm_ozyZC1cPVHKzXfi_efZqJrrD0r1RQz3aUnKS6YytEhXTlew)![](https://lh5.googleusercontent.com/0BL3NkNNvpgpE4gUrSQOuPo4uge82dN8oW1BepaXdb07G0utfknzxXOBRqa4UlGfYMo3QyKbdAapv58oOO5il-TGTDf-vwRnLUfEkepIeIKsSNTTmhHBupXOcYVUfohTtPMOhba3)![](https://lh5.googleusercontent.com/qhOjhO4QyRPaM5mdvc0s2dpiGti24LeIiJeZM6H_ycwgkUrGWHoI1WoQajFFAdnjaI0f30eX6CjjgUU9nFcn2S5_iVBiHXF44wSVcz9ckHTfGsun1xNDWVJ8bnA49NGK8oOfTC-K)![](https://lh3.googleusercontent.com/QAK6DJEhmKk5X_Bg5rO57g0QOiv0FmR8T7qom5sZWD5SZQ-Ncd9kIUs7ARqsc_6mFVwYwFoVaoVB39NXSC-udVGjON53jAPLWUyQTwLx50RnxEWK8q3r5VT6BH50uQOoIXuA4huE)

![](https://lh5.googleusercontent.com/wH2wxvgCa5BKGV6WNF7xhto05zcvCSR9Y2Ja8ZBavc31Z5tTymY36JI1Qr9ir9jjVxnBJ91g-68XJtZGsOHHUDupWMpllBsmLR3SLy0hNBORjgEqFdhshSWyZgeDG90bXhMXgv8R)![](https://lh3.googleusercontent.com/kwbdccy4A6v7HhjWn8FMj86fpJMCjZXiJR_x63XgXEGurAP0PwTJZd4Qt2up_JTHQYgVjqweb49BtQ4zQUz3c_FPzAqtcRfzri7Ss-LbhwdeqYT-NmHVBbFtXNKD9Hrfxhsbt_ug)![](https://lh3.googleusercontent.com/bsvezhkenT1JyNjrqzVNexzvSosuXKYyWp4n6JYQ3WGnD39RS6d7-HxpCVLOcajl6oc1WDQXT3g44PCpp9Oh-9JcSbJfXpezrCc9d__ohgX7pO3vtyo5qFiEgJPl43d7LKXQHX4E)

### 2.2 App Functions

CuddleBug offers the following features:

-   Account authentication through email or google account

-   Viewing all users on the application

-   Adding and removing friends and viewing movies liked in common

-   Viewing movie titles, posters, ratings, descriptions and year of release

-   Liking and disliking movie selections

-   Viewing previously liked movies

### 2.3 User Characteristics

End users consist of a wide variety of demographics, mostly those who are subscribed to streaming services or rent/purchase movies often.

|

Age

 |

Varies anywhere from young adolescents to elderly

 |
|

Gender

 |

Equal use from all genders

 |
|

Language and Culture

 |

Currently English and French are the only languages supported

 |
|

Educational Level

 |

All levels of education. 

 |
|

Frequency of Use

 |

We expect a high frequency of use, specifically concerning the MyList feature

 |
|

Motivation to Use

 |

Motivation depends on each user's movie/tv watching habits. More motivation amongst those who spend more time watching. 

 |

These characteristics do not reflect any data collected during testing. They are only projections of our expected user base as we have yet to publish the app to market. Once published, Google Analytics will provide a detailed report on more specific characteristic details.

### 2.4 Constraints

The CuddleBug experience is constrained by missing features that we look to implement moving forward. Several functionalities including the ability to filter and curate a given user's movie selection based on their preferences and a set of filters. Such a feature would require database manipulation, where custom queries could be sent to our API and processed in a way that returns the specific data requested. From the front end, a dropdown menu or search bar would need to be added so that users can make these requests from their end. Fortunately this was the only deliverable featured from our project proposal which did not make its way to the current application build.

The friends feature is also constrained by the inability to search for specific users by name or email. Currently, users can only add friends from a list that includes all other registered accounts on the app. With an increase in account registration, this will need to be addressed as the list of friends to add will quickly become too large to scroll through. This feature can be implemented by adding additional query options to our friends endpoint such as by name, email or phone number.

Given the time restrictions imposed by our deadlines, the MyList, ExploreMovies and Friends features are implemented in a basic manner to lay the foundation for development moving forward. We hope to better curate movies and improve our apps connectivity in the iterations following this report.

### 2.5 Dependencies and Implementations

-   Compiled in compileSdkVersion 28

-   minSdkVersion 24

-   Google firebase authentication and firestore

-   CardStackView by github.com/yuyukaido

-   License agreement link <https://opensource.org/licenses/Apache-2.0>

3.Requirements
--------------

### 3.1 Functional Requirements

The following requirements do not reflect the final version of CuddleBug. Version updates will continue following 12/09/2020.

Use Cases

|

MyList Class

 |
|

Actors

 |

Teens, Adults

 |
|

Description

 |

The user is able to view all of the movies they have previously liked and read additional information regarding the movie such as plot, actors, release date and genre. 

 |
|

Data

 |

List of ItemModel (Movie Objects) retrieved from our REST API

 |
|

Stimulus

 |

Android Lifecycle Method → onCreateView triggers an asynchronous call to fetch for all liked movies given a user_id

 |
|

Response

 |

A ListView is populated with the movie data and each corresponding movie is shown with its title and release date

 |
|

Comments

 |

For new users, the list will appear empty as they have no previous entries. 

 |

|

FriendList Class

 |
|

Actors

 |

Teens, Adults

 |
|

Description

 |

The user is able to view all of the friends they've connected with on the application. In addition, they are able to view the movies which they have in common. 

 |
|

Data

 |

List of Friends (Friend Objects) retrieved from our REST API\
List of Movies in Common between 2 users → retrieved from API

 |
|

Stimulus

 |

Android Lifecycle Method → onCreateView triggers an asynchronous call to fetch for all friends and movies in common

 |
|

Response

 |

A ListView is populated with the friend data and each corresponding friendship is displayed with their name

A Fragment is presented when a ListItem is clicked, displaying movies in common

 |
|

Comments

 |

For new users or individuals who haven't added friends, there will be no content displayed in the ListView

 |

CardStack

|

CardStack Class

 |
|

Actors

 |

Teens, Adults

 |
|

Description

 |

The user is able to view all of the new movies - objects they haven't liked before. Users will be able to swipe left or right depending on if they disliked or liked the movie, respectively. 

 |
|

Data

 |

List of ItemModel (Movie Objects) retrieved from REST API

 |
|

Stimulus

 |

Android Lifecycle Method → onCreateView triggers an asynchronous call to fetch for all movies

 |
|

Response

 |

A CardStackView is populated with all the movies, one card is presented at a time to the user

 |
|

Comments

 |

There is a limit of 140 movies stored within the database. 

 |

Use Case Diagram

![](https://lh3.googleusercontent.com/omYWyAWjIf-pxX-CQTswmUWvxZHaS-nEMgLwbjrUkfjtHxSMF7eRJ0I0JqMEO_WnuJFzqzfjYCuMokYYnEoaSjp3HWDlydRrcsqIqWc1mo4joVJAEmprdN3f71lvTJHQMPgBT9Fa)

### 3.2 Non Functional Requirements

-   Application should feel fluid and responsive

-   Support a second language

-   Allow Google SignIn and Authentication

-   Be visually appealing and fun to use

4\. Object-Oriented Description
-------------------------------

### 4.1 Class Description

|

Class

 |

Functions

 |

Description

 |
|

LoginActivity

 |

-   firebaseAuthWithGoogle

-   signIn

-   UpdateUI

-   onActivityResult

 |

Handles user login functionality.

A user can either login using Google Account Authentication or with a previously registered email. 

 |
|

RegisterActivity

 |

-   callAPI

-   registerNewUser

-   doInBackground

 |

Registers a new account using a provided email, password and account name

 |
|

SplashActivity

 |

-   onCreate

 |

Loading screen that displays when app is started. Allows sufficient time for data to be requested from API.

 |
|

ExploreActivity

 |

-   onCreate

 |

Generates main explore page and declares MyList, Explore and Settings fragments

 |
|

Explore_Movies_Fragment

 |

-   GetLikedMoviesQuery

-   onPostExecute

-   AsyncGet

-   AsyncPost

 |

Populates CardStack object with a list of movie titles.

If user swipes right on images, the relevant movie information is posted to MyList

 |
|

My_List_Fragment

 |

-   callAPI

-   AsyncGetLiked

-   onPostExecute

-   MovieDetailsQuery

-   onPostExecute

 |

Populates ListView with liked movies from the user's account

 |
|

Settings_Fragment

 |

-   openDrawer

-   closeDrawer

-   Logout

-   onNavigationItemSelected\
 |

Displays user's account information and app instructions.

Can navigate to side menu for friend adding features

 |
|

CardStackAdapter

 |

-   ViewHolder

-   getItem\
 |

Handles swiping card functionality for movie cards. The entire list of movies is pulled from the movie API and displayed on screen.

Behaves according to swipe direction and posts results in MyList.

 |
|

AddedFriendsFragment

 |

-   CallApi

-   AsyncGetFriends

-   onPostExecute

 |

Displays the user's list of friends based on who they've added.

Once a friend's username is clicked, their list of movies is generated

 |
|

FriendModel

 |

-   FriendModel

 |

Returns all relevant friend data from local friend list

 |
|

Friends

 |

-   callAPI

-   callAddFriendAPI

-   onPostExecute

-   userAdapter

 |

Locates other user accounts on the app and displays them for the user to add

 |
|

MoviesFriendFragment

 |

-   callAPI

-   AsyncGetCommonMovies

-   onPostExecute

 |

Compares and retrieves the all movies in common form user account and friend account. 

 |
|

ItemModel

 |

-   getPoster

-   getTitle

-   getYear

-   getRating

-   getImdbID

 |

Returns all relevant movie data from the local movie array list.

 |

![](https://lh5.googleusercontent.com/jYfBN69kvhKiFxWarpvdhOckBCAZPrtnGcZsnz-W1mkbhe5LAlIet1VsPG2oL88xZl1PJmJLXblABuazy20cl46zITcYmuWR7uoPRyeLSzgFt8zb5Hpmm9dTS1vAspulCuNucHK0)

6 Progression
-------------

### 6.1 Progress Report

|

Dates

 |

Activities

 |
|

September 28, 2020

 |

-   First meeting takes place,

-   Decided on app to be a movie selection service,

-   Github created,

-   Project proposal written,

 |
|

October 5, 2020

 |

-   Searched for movie API,

-   Created starting layout:

-   Login page,

-   Main page,

 |
|

October 12, 2020

 |

-   Reading week,

 |
|

October 19, 2020

 |

-   Starting working on login page using firebase,

-   Pages created:

-   MyList,

-   Explore Movies,

-   Settings,

-   Created tab at bottom of screen to easily navigate through pages,

 |
|

October 26, 2020

 |

-   Continued work on login page:

-   Google authentication,

-   Regular email login,

-   Registration page,

 |
|

November 2, 2020

 |

-   Started working on swiping functionality ,

-   Used hardcoded movies to test,

 |
|

November 9, 2020

 |

-   Swiping functionality completed,

-   Login page completed:

-   Google authentication completed,

-   Basic login completed,

-   Registration completed,

 |
|

November 16, 2020

 |

-   Empty fragment created containing:

-   Friends list,

-   Add friends,

-   logout,

 |
|

November 23, 2020

 |

-   Movie database created,

-   API documentation/instructions written and shared,

 |
|

November 26, 2020

 |

-   Swiping function works with movies from database,

 |
|

November 28, 2020

 |

-   Friends list completed,

-   Add friends completed,

 |
|

November 30, 2020

 |

-   Logout functionality completed,

-   Content added to settings page,

 |
|

December 7, 2020

 |

-   Presentation day,

 |
|

December 8, 2020

 |

-   App instructions added to settings page,

-   App functional in french,

-   Movie descriptions added,

 |
|

December 9, 2020

 |

-   Project completed,

-   Submitted to mylearningspace,

 |

### 6.2 Testing 

Credentials used for testing:

Username: cp470@android.com

password:123456

Pasword: 123456

|

Page 

 |

Action

 |

Result

 |
|

Login Page

 |

Invalid credentials

 |

-   Toast message: "Login Failed"

-   Both fields remain filled with invalid credentials until user deletes them

 |
|\
 |

Valid credentials

 |

-   Takes user to MyList only if user has registered properly

-   All of users data will be saved after logout as information is saved to database

-   Sign in remembered until logout

 |
|\
 |

Google sign in

 |

-   Button activates pop up that allows user to choose google account to use to sign in

-   Clicking account will lead to user being sent to main page in the app

-   For first time users this also acts as a registration page

 |
|\
 |

Registration page

 |

-   If all fields empty

-   Toast message: "Please enter email..."

-   If only email field entered:

-   Toast message: "Please enter password!"

-   If email and password fields entered:

-   Toast message: "Please enter a Name!"

-   If only confirm password field empty:

-   Toast message: "Password does not match Confirmed Password!"

-   If password and confirm password fields do not match:

-   Toast message:  "Password does not match Confirmed Password!"

-   If all fields completed correctly:

-   Toast message: "Registration success"

-   Takes user to 'MyList' page

-   Password only accepted if length is greater than 5 characters

-   Username only accepted if it is in email format:

-   Example: user@application.com

 |
|

Logout

 |

Functionality

 |

-   Found on Settings page after top left image clicked

-   Message displayed:

-   "Are you sure you want to logout?"

-   If "NO" clicked user stays on settings page

-   If "YES" clicked user is logged out properly and will have to log back in to access account again

 |
|

MyList Page

 |

Purpose of page

 |

-   This page only displays data that consists of movies the user has liked

 |
|\
 |

Remove movie

 |

-   Cannot currently remove movies from list

 |
|\
 |

Click on a movie

 |

-   This displays movie description

-   To make movie description disappear:

-   Click on movie title again

 |
|

Explore Movies Page

 |

Purpose of page

 |

-   To like and dislike movies

 |
|\
 |

Swiping

 |

-   Swipe left:

-   This action dislikes a movie

-   Toast message: "Disliked"

-   Swipe right:

-   This action likes a movie

-   Toast message: "Liked"

-   Swipe up and down:

-   User is unable to swipe up or down

 |
|

Settings page

 |

Purpose of page

 |

-   Information displayed:

-   Users name

-   Email

-   Instructions:

-   Explore movies page

-   Settings

 |
|\
 |

Top left image clicked

 |

-   Displays fragments:

-   Friends List

-   Add Friend

 |
|

Add friends page

 |

Purpose of page

 |

-   To add friends

-   Contains a list of current users 

 |
|\
 |

Adding functionality

 |

-   Click users plus button to add them

-   The added users name is taken away from list after added

-   No functionality to remove friend

 |
|

Friends list

 |

Purpose of page

 |

-   Contains list of friends,

-   User must select a friend to see movies both users have liked

 |
|\
 |

Functionality

 |

-   Can click on friends and will take user to a section that shows common liked movies

-   User cannot remove any movies from this list

-   Cannot remove friend(s)

 |

### 6.3 Version History

|

Iterations

 |

Description

 |
|

Iteration 1

 |

Basic XML page layout designed and created. This includes the login activity and the explore activity

 |
|

Iteration 2

 |

Submitted iteration 1 buttons, swiping function with hardcoded movies.

 |
|

Iteration 3

 |

Swiping through movies populated by database. Fully functional database that handles movies and users.

 |
|

Iteration 4

 |

Add friends, friends list and my list page completed.

 |

7\. Manual
----------

For testing purposes, use the following login information:

Credentials used for testing:

Username: cp470@android.com

password:123456

Or register your own account!

### 7.1 Registration/Login

Registration is handled by Google Firebase Authentication. Registration requires a user to either link an active google account or create an account by providing their own email and password.

-   For Google SignIn, simply click the sign in button with the Google logo next to it

![](https://lh3.googleusercontent.com/zSEjhaAGBMip0_0G9Ph0xWNHLCX1qkrdxU2kFKhEeQjrKJds81g1LQzK2eZQEYq-4NzM1I8b8nsS8dxW0p3ZTgphtRd6zNOZHOvJv_Q-LThwEjWHHotEcfarTeoRVdCpM0PJqCgg)

-   For non-Google accounts, users must click the register button which and fill out all the required information under the registration page

![](https://lh6.googleusercontent.com/_D13ETon7CxuQgcHggnpW9zjdOo9F5V-jH7QthNZd5HPzIh0oSr50hTkV2g3hCTFnv04QUJ3KaHzkKTq3td2-2o-nqB42a6xn74rPI9WkQ9StWMi-5bIe08c27iBIAGkBhtQaEFy)

![](https://lh4.googleusercontent.com/Uuejt6eNlogCrIukg0psiLL0fVt8QwdmivKAFwWqGlSzEzmZhWX0-pGTiVsNe1qXamXBHaRNx23_1lEhfk27eg3mI4wlBionjHyk7i1utS49zyDfysgv6__vf-44W84Wo705yHj6)

-   Once an account has been created, users can Login by entering their credentials in the Login page

![](https://lh6.googleusercontent.com/o5CRiCiZczao8HUXcwBMs7H9whDB7Gw66Fxw_t_Rl9x-XD1L-sj6WtlWmjgQbbB-JZLjxVIw1wDzI_D-yVIQ5h92abywY5APQPcT57v4_BkgoAyUPrZTJUy_1sZOIyt03225YYP6)

### 7.2 Liking/Disliking Movies

Once logged in, users will be brought to the Explore page, where movies appear in a stack-like display. By swiping right on the movie poster, it is added to the user's movie list. If disliked, it will be discarded and essentially popped from the stack.

![](https://lh6.googleusercontent.com/6zU0Sl_Afg9erlCdB6KPqpPPg9kwQl-tTB5RCwefaDHmr0bqua8KxLUxQyQJMpG-Z8eUQCyxJzlAO_3DKM8utAitrjC9-2M6rW_MTCSjjH-d-KuHZxiKKUkmaL5CPt3vmLyDnRtS)

### 7.3 MyList

The MyList page displays the titles of the movies that have been liked by the account alongside their year of release. Click on the movie title for a more detailed description

 ![](https://lh4.googleusercontent.com/FKh3PKTTBqdCI7PWkSjkG1luz8bfDHd2gs-yDyUR7LFq2M694Dn_xVxjMgpaEVgcySsDJ7j2Yl06yZPjnKZ1LTawVXCzc7X1xpGU8OKmrJenVIv-FAFFROLZoaNusg8zANKeA10Y)![](https://lh3.googleusercontent.com/mZT3Q0dW3lchbTwBn94c6Lh6jqUmHfiMDo_vDolKwhGleDWjzhCNcWLVXPi5Ev9vvZpi_mx0cq5Hjf5O0XPOhavWM11jrqrA4nxM1Q8SlvcrofcA9xWUfcF-2liVWSld5mHhrHqh)

### 7.4 Settings

From the settings page, a user is able to view their information and manage their account. By clicking on the 3 lines on the top left of the page, additional options are revealed

![](https://lh5.googleusercontent.com/C45rT2uMUzhN52rJP60XM_aMNhxuX04zHRryPtK9baOGDZl2LSlOWH3t6H3Mc2B8X9-Uy-nE_Flp6l3rUxpX6BDj6X27XmmfZrkdYCn2VL-39Gn7VqHH-7Tdmlo-ltRd4tuA5Vrv)![](https://lh4.googleusercontent.com/vyYpJqstx0pc17P40TpxGO1JdY0w32ApUpBGN1vxrsIlVuj0Kc8v6hyRDMv5dY8xYyr6Ci98eTOadIHjb6dphRzrvpl8kM0tT5fMw6K3zfkhUpz9QcQNab7cjeFWiadC1z9Z9uRp)

The settings pullout menu will either redirect to the Friends List page, the Add Friend Page or the Logout button

-   Friends List displays the user's current friend list. By clicking on a specific friend, a list of common movies between accounts becomes visible

![](https://lh6.googleusercontent.com/zQid1_VGdadGovu9WQAyr4R-JlkoOWfurBZ5DVagFSQAOpmt3-PB-QxRlxSDTIzdMuIpqp6z0_FeNYGNnHAObMcLMSR-wGHrk6Vm9nRedOJApPpHJYvwryEy7JL0yWD9g9o_8UMP)![](https://lh5.googleusercontent.com/VrtK9hJgCb9uzTRssYgqn6tuBixzq2qmDVvFobvayRnLfZOp8FB9aaD3wWnTP7WR57thqyJZ7UXGk0wVryofa_CQoOuBZmaeXtUurs7RnXfiC4jD2Ea2a5PHPtxweVHl3WUsZEYH)

-   Friends can also be added from the Add Friends page

### 7.5 Logout

To logout click the logout button and confirm by clicking yes

![](https://lh3.googleusercontent.com/XJMYcO9NQkAhQYWnt84ORGZtXsEg4En9q4PywG_tCBwksmGvPeQICQuGSG5wX0fcDl5HPreMvK5XGqTqUKbDEfmSbceo7BffcPtTl9fw6lAi8H265C91QCSEH7-lkVAcBSrOg00u)

8\. References
--------------
