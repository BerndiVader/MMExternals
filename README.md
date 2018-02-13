# MMExternals

How to use? Grab the modules you need from [externals](https://github.com/BerndiVader/MMExternals/tree/master/externals) and install them on your server in `plugins/MythicMobsExtension/externals` reload MythicMobs or restart your server and the newly added modules are avaible.

### Externals so far:

## chatlistener
### Mechanic

`chatlistener{phrases=[STRING]||[ARRAY];period=[VALUE];radius=[RANGEDVALUE];breakonmatch=[BOOL];breakonfalse=[BOOL];inuseskill=[SKILLNAME];matchskill=[SKILLNAME];falseskill=[SKILLNAME]} @PLAYERTARGETERS`

Use this skill to listen to the targeted players chat for period of ticks. If one of the phrases, or any if empty, match the matchskill is excuted, if not the falseskill is executed. Use breakonmatch and breakonfalse to cancel the skill if match or if no match. Radius is the range the player needs to be. This can be a ranged value like 2to5 or <10 that stuff. Use the inuseskill to tell others that the mob is already talking to someone else.

Examples:

```yaml

#skill yaml
match:
  Skills:
  - message{msg="You entered the right password!"} @trigger
false:
  Skills:
  - message{msg="Wrong password!"} @trigger
inuse:
  Skills:
  - message{msg="Im busy right now"} @trigger
  
#mob yaml
ChatMonkey:
  Type: zombie
  Health: 10
  AIGoalSelectors:
  - 0 clear
  Skills:
  - chatlistener{phrases=supersecretpassword;period=240;inuseskill=inuse;matchskill=match;falseskill=false} @trigger ~onInteract
  
```

