# bind

## Name

*bind* - overrides the host to which the server should bind.

## Description

Normally, the listener binds to the wildcard host. However, you may want the listener to bind to
another IP instead.

If several addresses are provided, a listener will be open on each of the IP provided.

Each address has to be an IP or name of one of the interfaces of the host. Bind by interface name, binds to the IPs on that interface at the time of startup or reload (reload will happen with a SIGHUP or if the config file changes).

If the given argument is an interface name, and that interface has serveral IP addresses, CoreDNS will listen on all of the interface IP addresses (including IPv4 and IPv6), except for IPv6 link-local addresses on that interface.

## Syntax

~~~ txt
bind ADDRESS  ...
~~~

**ADDRESS** is an IP address to bind to.
When several addresses are provided a listener will be opened on each of the addresses.

## Examples

To make your socket accessible only to that machine, bind to IP 127.0.0.1 (localhost):

~~~ corefile
. {
    bind 127.0.0.1
}
~~~

To allow processing DNS requests only local host on both IPv4 and IPv6 stacks, use the syntax:

~~~ corefile
. {
    bind 127.0.0.1 ::1
}
~~~

If the configuration comes up with several *bind* plugins, all addresses are consolidated together:
The following sample is equivalent to the preceding:

~~~ corefile
. {
    bind 127.0.0.1
    bind ::1
}
~~~

The following server block, binds on localhost with its interface name (both "127.0.0.1" and "::1"):

~~~ corefile
. {
    bind lo
}
~~~

## Bugs

When defining more than one server block, take care not to bind more than one server to the same
address and port. Doing so will result in unpredictable behavior (requests may be randomly
served by either server). Keep in mind that *without* the *bind* plugin, a server will bind to all
interfaces, and this will collide with another server if it's using *bind* to listen to an interface
on the same port. For example, the following creates two servers that both listen on 127.0.0.1:53,
which would result in unpredictable behavior for queries in `a.bad.example.com`:

```
a.bad.example.com {
    bind 127.0.0.1
    forward . 1.2.3.4
}

bad.example.com {
    forward . 5.6.7.8
}
```