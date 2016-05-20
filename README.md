# weatherwatch
Website based on information from OpenWeatherMap.org 

Created starter project from https://start.spring.io/

Using ~/.weatherwatch/weatherwatch.properties to hold sensitive data.

Signed up to OpenWeatherMap.org and generated an API Key, placed in weatherwatch.properties along with proxy details

    weatherwatch.api.key=0123456789abcdeffedcba9876543210
    weatherwatch.http.proxyHost=proxy.example.org
    weatherwatch.http.proxyPort=8080

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

See https://en.wikipedia.org/wiki/Kelvin

    degreesCelsius = degreesKelvin - 273.15
    degreesFahrenheit = (degreesKelvin * 9.0 / 5.0) - 459.67


OpenWeatherMap.org suggests that we only send requests every ten minutes so we need to do some *caching* 

> **How to get accurate API response**

> 1 Do not send requests more then 1 time per 10 minutes from one device/one API key. Normally the weather is not changing so frequently.

> 2 Use the name of the server as api.openweathermap.org. Please never use the IP address of the server.

> 3 Call API by city ID instead of city name, city coordinates or zip code. In this case you get precise respond exactly for your city.

> 4 Free account has limitation of capacity and data availability. If you do not get respond from server do not try to repeat your request immediately, 
> but only after 10 min. Also we recommend to store your previous request data


## TO DO
- Cache/orchestration layer to add timezone data and limit calls to every ten minutes at a time - DONE

- presentation layer - DONE

- Time Zones!
  - today's date in the city time zone - DONE
  - Show sunset and sunrise in the city time zone - DONE
  - Give user the option to show these in their local time zone - TODO

- Additional web-level tests including integration tests to show that the cache is doing its work

- Its ugly and not static - use Bootstrap to make it pretty and JavaScript to make it dynamic

## Links
[WebJars](http://www.webjars.org/)

[Weather Icons - 222 font icons inspired by Font Awesome and designed for Bootstrap](https://erikflowers.github.io/weather-icons/)
and a [WebJar for weather-icons](https://github.com/webjars/weather-icons)

[Bootswatch](http://bootswatch.com/) and a [WebJar for bootswatch](https://github.com/webjars/bootswatch)

[Using SVG | CSS-Tricks](https://css-tricks.com/using-svg/)

[Spring resource handling - Bower, less.js](https://github.com/bclozel/spring-resource-handling/blob/master/README.md)
