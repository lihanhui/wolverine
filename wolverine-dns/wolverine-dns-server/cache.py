import record
import configure
from threading import Thread
from kazoo.recipe.watchers import ChildrenWatch
from kazoo.client import KazooClient

import time

class Cache( Thread ):
    def __init__(self):
        Thread.__init__(self)
        self.hostmap = {}
        self.servicemap = {}
        self.masterUri = None
        zk = KazooClient(hosts=configure.Config.getConfig().getVal("zks"))
        zk.start()
        
        ########################################
        @zk.ChildrenWatch("/wolverine/scheduler")
        def watch_children(children):
            print("who is calling")
            print(self)
        print "xxxxxxxxxxxxx" 

    def getRecrod(self, hostname):
        return self.hostmap[hostname];

    def getRecords(self, service):
        return self.servicemap[service]

    def run(self):
        while(True): # to load dns records from wolverine-scheduler (may be proxied by api server)
            time.sleep(10)

