CSC 369
Lab 2
Lana Huynh

Problem 1:
I split each line of the access log on whitespace and I took the sixth index to get the URL path which was my key. I
added 1 to each URL path, and in the "reduce" part I summed them all together. Then, I created another MapReduce model
and ran the first output file through that to essentially swap the url path and count in order to implicitly sort by
request count.

Problem 2:
I split each line of the access log on whitespace and I took the eighth index to get the HTTP response code which was my
key. I added 1 to each key, and in the "reduce" part I summed them all together.

Problem 3:
I split each line of the access log on whitespace and I took the 0 index to get the hostname and the 9th index to get
the bytes. Then, I checked if the hostname matches my specified hostname, and if it does, I would add it to the total
sum of the bytes.

Problem 4:
I split each line of the access log on whitespace and I took the 0 index to get the hostname and the 6th index to get
the url. Then, I checked if the url matches my specified url, and if it does, I add up the number of counts. Then, I
used another MapReduce model and ran the first output file through that to swap the url path and count in order to
implicitly sort by request count.

Problem 5:
I split each line of the access log on whitespace and I took the 3rd index to get the date in string format. I
converted this date into SimpleDateFormat and to easily extract the month and year. I joined them together and counted
the number in each month/year.

Problem 6:
I ran the same process as problem 5, but I also got the 9th index to get the bytes. I also extracted the day from the
date, and summed up the total number of bytes by calendar day.
