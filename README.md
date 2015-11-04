# Airlines
application which helps to control process of creating and managing flight team and flights

********************************** TASK DESCRIPTION *******************************************

Aviacompany has different flights.
  This application can:
- find flight by number;
- find flight by parametras 'from', 'to', 'date of departure';
- sort flight by number or price.

Also we have to different roles: Dispatcher and Administrator. 

  Dispathcer create flight team (pilots, navigator, operator, flight attendent) and control flight status.
If he have problem with creating flight team he can send application to Administrator.<br>
  Administrator controls list of flights ( edit, add or delete flight), list of staff (also edit, add, delete staff). Also
Administrator gives response on Dispatcher's applicatoin and closes them with status 'apply' or 'deny'.

********************************** INSTRUCTION *************************************************

Before starting applicaton please don't forget about turn on service MySQL also please add libraries from folder Airlines/lib
(https://github.com/feanor777/Airlines/tree/master/lib) to folder '&lt;APACHE_TOMCAT_HOME&gt;/lib' !
After starting application, use this address in browser http://127.0.0.1:8080/Airlines
<br>
To log in system as administrator or dispatcher use next login and passwords:<br>
login         password<br>
admin     -   admin<br>
dispatcher -  dispatcher<br>
You have to have internet connection for using all capabilities of applicaton.

If you have any question please write me.
Thank you for the attention :)
