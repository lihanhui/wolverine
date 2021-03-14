# wolverine
wolverine-dns: service discovery based on dns;
Implemented by dnspython.

Request/response:
1. to get record of one hostname;
request:
{
    "method":"translate-domain-name",
    "hostname": "hostname on which service is running"
}
response:
{
   "hostname": "hostname on which service is running"
   "record": "192.168.1.155"
}

2. get a list of records of one service;
request:
{
    "method":"translate-service-name",
    "service": "the name of service which will be translated into a list of records"
}
response:
{
    "service": "the name of service which will be translated into a list of records"
    "records":["192.168.1.154","192.168.1.156"],
}
