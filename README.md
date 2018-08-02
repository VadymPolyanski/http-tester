# http-tester
In config file you can write 'rps', 'url' and max connection pool size(with good internet connection I 
entered 4096 and with bad - 32) paramethers.

Run Main and program will do send  every seconod 'rps'(from config) number of requests for 'url'(from config).
Also after each done cycle it'll print the statistic.
If your computer or internet connection is not enought for this rps, program will send all requests and print how
many seconds it needed.
You can close program by Ctrl+C combination or by stopping code in your IDE.
