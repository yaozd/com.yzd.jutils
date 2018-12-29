- [level=error msg="Handler for GET /containers/XXX/json returned error: No such container](https://github.com/moby/moby/issues/29056)
```
I did have a quick looks at the kubernetes source, 
and it looks like their reconciliation loop defaults to 60 seconds.
Each 60 seconds, they poll each Pod's containers to check if the actual state matches the expected state,
so it's possible that somehow it's using the wrong information there, but I'm not familiar enough with the code base to say anything sensible other than that.
```