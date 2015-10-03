# File Formats

The ECP application uses the files in this folder to get the available topic
list (**available_topics.txt**) and to answer to topic number requests with an 
appropriate IP and port number of the TES program (**topics.txt**).

There is also a file called **stats.txt** to which the student stats sent by the 
TES application are stored.

Below are the descriptions of the expected contents of the files.

## available_topics.txt

This file contains the available topics, **one per line**. If no topics
are available, it can be left empty or deleted.

## topics.txt

The file contents syntax is the following:

```
topicNumber [topicNumber ...] IP PORT
```

Multiple lines are allowed. Each line should respect the syntax above.

Where `topicNumber` is an integer indicating the topic number. `IP` and `PORT`
are the IP address (or hostname) and the port of the TES application, respectively.

If the same `topicNumber` is associated with different TES addresses (*i.e* the same `topicNumber`
appears in more than one line), the address of the TES application in the first matching line is used.

If no TES are available, this file can be left empty or deleted.

