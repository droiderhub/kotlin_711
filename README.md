# kotlin_711
practicing all git commands
adding simple library replacing toast

>Add it in your root build.gradle at the end of repositories:

...allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
  
  
  >Step 2. Add the dependency
  
  ...dependencies {
	        implementation 'com.github.sajittarafdar:kotlin_711:Tag'
	}
