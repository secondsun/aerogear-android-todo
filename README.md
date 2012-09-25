AeroGear Android Prototype
==========================

Note that to build this project with Maven, you'll need to use the Android Maven SDK Deployer
(https://github.com/mosabua/maven-android-sdk-deployer) in order to install the Google extension
APIs into your local Maven repository.  To do this, just do

```bash
$ cd <wherever you installed the android-sdk-deployer>
$ mvn install -P 4.1
```

(note: make sure you've already installed all the relevant Android packages to your local
SDK installation via the "android" tool, as described in the documentation at https://github.com/mosabua/maven-android-sdk-deployer/blob/master/README.markdown.

Then running "mvn install" from the root directory of *this* project should successfully build everything, run the
tests, and generate you a shiny new APK all set to install on your phone.
