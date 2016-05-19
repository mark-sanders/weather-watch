# weatherwatch
Website based on information from OpenWeatherMap.org 

Created starter project from https://start.spring.io/

Using ~/.weatherwatch/weatherwatch.properties to hold sensitive data.

Signed up to OpenWeatherMap.org and generated an API Key, placed in weatherwatch.properties along with proxy details

    weatherwatch.api.key=0123456789abcdeffedcba9876543210
    weatherwatch.http.proxyHost=proxy.example.org
    weatherwatch.http.proxyPort=8080
    weatherwatch.http.proxyPort

City list accessed from: http://bulk.openweathermap.org/sample/city.list.json.gz

    ...
    {"_id":2643743,"name":"London","country":"GB","coord":{"lon":-0.12574,"lat":51.50853}}
    ...
    {"_id":1819729,"name":"Hong Kong","country":"HK","coord":{"lon":114.157692,"lat":22.285521}}
    ...

Testing my API key with cURL:

    masander@LGB03000145 MINGW64 ~/Downloads
    $ curl "http://api.openweathermap.org/data/2.5/weather?id=2643743&appid=0123456789abcdeffedcba9876543210"
      % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                     Dload  Upload   Total   Spent    Left  Speed
    100   434  100   434    0     0   1990      0 --:--:-- --:--:-- --:--:--  1990
    {"coord":{"lon":-0.13,"lat":51.51},"weather":[{"id":803,"main":"Clouds","description":"broken clouds",
    ...
    "id":2643743,"name":"London","cod":200}

Testing error output with cURL:

    masander@LGB03000145 MINGW64 ~/Downloads
    $ curl http://api.openweathermap.org/data/2.5/weather?id=2643743&appid=99999
      % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
      0     0    0     0    0     0      0      0 --:--:-- --:--:-- --:--:--     0
    100   107  100   107    0     0    457      0 --:--:-- --:--:-- --:--:--   457
    {"cod":401, "message": "Invalid API key. Please see http://openweathermap.org/faq#error401 for more info."}


I've captured these results as "london.json" and "error.json" in src/test/resources


In the `WeatherResult` JSON objects I've used `long` for time stamps as you won't mistake `0L` for a real time stamp, but for temperature and pressure I'm using `Double` and `Integer` to avoid confusion when values are missing. 

I've left temperature in Kelvin at this stage and intend to map to Fahrenheit and Celsius at the presentation stage. 