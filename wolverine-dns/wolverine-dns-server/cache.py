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
            for node in children:
                if "lock" in node:
                    value, stat = zk.get("/wolverine/scheduler/"+node)
                    print ("get wolverine scheduler master: " + value)
                    self.scheduler_ip = value if value else None
                    return;
            self.scheduler_ip = None
        print "dns cache initialized ..." 

    def getRecrod(self, hostname):
        return self.hostmap[hostname];

    def getRecords(self, service):
        return self.servicemap[service]

    def run(self):
        while(self.scheduler_ip): # to load dns records from wolverine-scheduler (may be proxied by api server)
            # we get scheduler ip from zk, it is written by wolverine scheduler master
            time.sleep(10)

