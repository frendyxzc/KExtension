# KExtension

[![](https://jitpack.io/v/frendyxzc/KExtension.svg)](https://jitpack.io/#frendyxzc/KExtension)

Android Kotlin Extension



## Usage

### 1. Add it in your root build.gradle at the end of repositories:

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

### 2. Add the dependency:

```
dependencies {
	compile 'com.github.frendyxzc:KExtension:0.0.2'
}
```

-----

## Extensions:

* Activity
	- gotoBrowser

* AppBarLayout
	- setAppbarScrollFlag

* EditText
	- hideKeyboard

* Handler
	- postDelayedToUI
	- postToUI

* ImageView
	- loadImage
	- loadImageCircle
	- loadImageRoundedCorners
	- loadImageFliter
	- loadImageColorHolder

* SearchView
	- hideKeyboard
	- setTextStyle

* String
	- containEmoji

* View
	- doAnimation

-----

## Base:

* Activity
	- BaseActivity
	- BaseFragmentActivity
	- BaseSwipeBackActivity
	- BaseSwipeBackFragmentActivity

* Fragment
	- BaseFragment
