import record
from threading import Thread
import kazoo.recipe.watchers
import time

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
            time.sleep(10)
