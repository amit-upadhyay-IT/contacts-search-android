# contacts-search-android
A library which fetches the contacts from mobile and allows to search over it.

To use this library:
1) git clone https://github.com/amit-upadhyay-IT/contacts-search-android.git

2) Change the first line of app level build.gradle file to: 
apply plugin: 'com.android.library'

3) Sync the project

4) Now you can use the Pre-defined classes for your purpose. EG: ContactsRecyclerAdapter, SearchActivity, etc;

5) To request permissions you can use this code:

        public static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;

        @Override
        public void onRequestPermissionsResult(int requestCode,
                                               String permissions[], int[] grantResults) {
            switch (requestCode) {
                case MY_PERMISSIONS_REQUEST_READ_CONTACTS: {
                    // If request is cancelled, the result arrays are empty.
                    if (grantResults.length > 0
                            && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                        // permission was granted, yay! Do the
                        // contacts-related task you need to do.
                        displayContactsList();

                    } else {

                        // permission denied, boo! Disable the
                        // functionality that depends on this permission or ask for permission again.
                        ActivityCompat.requestPermissions(MainActivity.this,
                                new String[]{Manifest.permission.READ_CONTACTS},
                                MY_PERMISSIONS_REQUEST_READ_CONTACTS);
                    }
                    return;
                }

                // other 'case' lines to check for other
                // permissions this app might request
            }
        }
        
----------------------------------
    
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        }
        else
        {
            displayContactsList();
        }


# License

        Copyright 2017 Amit Upadhyay

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

           http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
