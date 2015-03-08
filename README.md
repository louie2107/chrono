# Chrono

A simple Textview the counts up or down. Interval and font are configurable. Interfaces for event changes are also included.

# Get started
An example application is included in the project.

# Usage
XML
```xml
<RelativeLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/header"
        android:layout_width="match_parent"
        android:layout_height="200dp"
        android:background="#3F51B5">


        <io.samlewis.chrono.Chrono
            android:id="@+id/counter"
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:fontFamily="fonts/digital-7.ttf"
            android:gravity="center"
            android:textColor="#FFFFFF"
            android:textSize="70sp"
            tools:ignore="UnusedAttribute" />
            
    </RelativeLayout>
```
# Future
I plan on adding more features in the future, pull requests welcome.

License
--------
The MIT License (MIT)

Copyright (c) 2014 Myriad Mobile, www.myriadmobile.com

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
