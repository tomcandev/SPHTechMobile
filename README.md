# SPHTech Mobile
## App Description
The app displays the amount of data sent over Singapore's mobile networks from 2008 to 2019 in online and offline mode with pagination

## How to use the app
Open the app with the Internet -> The app will show some items of the year data usage -> Scroll down to load more -> Click the image -> Show Dialog (Up and Down follow by Volume of Quarter)

## How to build the app
Should open project by Android Studio >= 3.6
There aren't specific keys or configs

## Challenge when building the app
I faced 3 problems when I build the app:
1. App Architecture: I would like to make an app with architecture to easy maintenance and scalable and testable. So I decided on a modularization app and applied Clean Architecture, MVVM. More details below
2. Dagger: There are many modules in the App Architecture, so it's hard to connect with each other. I created a BaseComponent in Base module and every feature module need to dependent to BaseComponent
3. Paging user list with online and offline mode: I used the Paging library in Android Jetpack and created a repository which responses to decide online (data remote by Retrofit) or offline (data local by Room)

## App Architecture
The image below shows the concept
![alt text](https://github.com/tomcandev/SPHTechMobile/blob/master/screenshot/modularization.png?raw=true)

I split the app into 4 modules:
Base Module: BaseActivity, BaseFragment, BaseComponent,...
Design System Module: Colors, Styles, Spacing, Icon Size, Font Size,...
Mobile Data Usage Module: Feature module to implement get data usage. This module is dependent Base module and design System module
App Module: included all modules to build the final app
The feature module can apply any architecture that they want. In this case, I apply Clean Architecture likes image below and apply MVVM in the Presentation layer
![alt text](https://github.com/tomcandev/SPHTechMobile/blob/master/screenshot/clean_architecture.png?raw=true)

## Advantage of Modularization:
Smaller components are easier to maintain.
Decrease build times
Components with high cohesion can be re-used again.
The team can decide architecture on own features
Some libraries in the project: ViewBinding, Dagger, Retrofit, RxJava. Jetpack components: ViewModel, Navigation, Paging, LiveData, Room

## Test
I don't have enough time to write all tests, so I try my best. The image below shows test coverage:
![alt text](https://github.com/tomcandev/SPHTechMobile/blob/master/screenshot/test.png?raw=true)

## Other comments
I try my best but I'm sure that it doesn't avoid to have some bugs
It's not config environment for dev, release and Staging, Production

## Demo
https://youtu.be/siVhM-MawA0

## Screenshot
![alt text](https://github.com/tomcandev/SPHTechMobile/blob/master/screenshot/main_screen.png?raw=true)
![alt text](https://github.com/tomcandev/SPHTechMobile/blob/master/screenshot/show_dialog.png?raw=true)
