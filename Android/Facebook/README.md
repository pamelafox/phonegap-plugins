# Facebook for PhoneGap on Android #
by Jos Shepherd

This is a PhoneGap plugin based on the Facebook Android SDK:
https://github.com/facebook/facebook-android-sdk

Basic calls to the authorize and graph API are supported - more to come.

Example use:

	appId = 123123123 // this is your facebook app id
	
	window.plugins.facebook.authorize(appId ,function(res){
		alert(res.name); // the authorized users name

		window.plugins.facebook.request("me/likes" ,function(res){
			alert(res.data[0].name); // the name of the first 'liked' item
		});
	
	});


## Licence ##

The MIT License

Copyright (c) 2010 Jos Shepherd

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.




