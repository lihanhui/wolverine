import record
from threading import Thread

class Cache( Thread ):
    def __init__(self):
        Thread.__init__(self)
        self.hostmap = {}
        self.servicemap = {}

    def getRecrod(self, hostname):
        return self.hostmap[hostname];

    def getRecords(self, service):
        return self.servicemap[service]

    def run(self):
        while(True):
            Thread.sleep(10)
