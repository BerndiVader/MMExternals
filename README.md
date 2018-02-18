# MMExternals

How to use? Grab the modules you need from [externals](https://github.com/BerndiVader/MMExternals/tree/master/externals) and install them on your server in `plugins/MythicMobsExtension/externals` reload MythicMobs or restart your server and the newly added modules are avaible.

### Externals so far:

## chatlistener
[file](https://github.com/BerndiVader/MMExternals/blob/master/externals/ChatListenerMechanic.jar)
### Mechanic

`chatlistener{phrases="[STRING]"||"[ARRAY]";period=[VALUE];radius=[RANGEDVALUE];breakonmatch=[BOOL];breakonfalse=[BOOL];inuseskill=[SKILLNAME];matchskill=[SKILLNAME];falseskill=[SKILLNAME];multi=[BOOL];meta=[TAGNAME]} @PLAYERTARGETERS`

Use this skill to listen to the targeted players chat for period of ticks. If one of the phrases, or any if empty, match the matchskill is excuted, if not the falseskill is executed. Use breakonmatch and breakonfalse to cancel the skill if match or if no match. Radius is the range the player needs to be. This can be a ranged value like 2to5 or <10 that stuff. Use the inuseskill to tell others that the mob is already talking to someone else. To make the mob multitalking, set multi to true and the mob is able to talk to more than one player simultaneously. Optional, set the phrases under "" to have spaces enabled. Additional all avaible variables like <target.name> etc... can be used.

+ phrases: the words, phrases the mob should listen to.
+ period: how long the mob listen, in ticks.
+ radius: the radius the targeted player need to be to get an answer.
+ breakonmatch: stop the listener if one of the phrases match.
+ breakonfalse: stop the listener if the chat message dont fit any phrase.
+ inuseskill: skill that is executed if the mob already listen to someone or if multi if the player is already in mobs attention.
+ matchskill: executed if match.
+ falseskill: executed if false.
+ multi: true/false(default) if more then 1 player is able to talk simultaneously.
+ meta: the metatag name where the message should be stored. ex: `meta=<trigger.uuid>lastmessage`this set a metatag for the mob with the triggers uuid + lastmessage. the value of the tag is the message.
+ removephrase: true/false(default) if the matched phrase should be removed from the message.
+ cancelmatch: true/false/default) cancel the message event if matched.
+ cancelfalse: true/false(default) cancel the message event if no match.

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
  - chatlistener{multi=false;phrases="supersecretpassword";period=240;inuseskill=inuse;matchskill=match;falseskill=false} @trigger ~onInteract
  
#mob yaml
ChatMonkey1:
  Type: zombie
  Health: 10
  AIGoalSelectors:
  - 0 clear
  Skills:
  - chatlistener{multi=true;meta=blabla<target.uuid>;phrases="help me,hilf mir";period=240;inuseskill=inuse;matchskill=match;falseskill=false} @trigger ~onInteract
  

#skill yaml
match:
  Skills:
  - pstance{s="You said: <mob.meta.blabla<trigger.uuid>>"} @self
  - message{msg="<mob.stance>"} @trigger
  - message{msg="Watch out for creepers, they are mean!"} @trigger
false:
  Skills:
  - message{msg="Sorry, i dont understand."} @trigger
inuse:
  Skills:
  - message{msg="You already have my attention!"} @trigger
  
```

## otcbiome
[file](https://github.com/BerndiVader/MMExternals/blob/master/externals/OpenTerrainBiomeCondition.jar)
### Condition

`otcbiome{biomes=[STRING]||[ARRAY];action=[BOOL]||[CAST]||[CASTINSTEAD]}`

**Requires [OpenTerrainControl](https://github.com/bloodmc/TerrainControl)**


## otgbiome
[file](https://github.com/BerndiVader/MMExternals/blob/master/externals/OTGBiomeCondition.jar)
### Condition

`otcbiome{biomes="[STRING]"||"[ARRAY]";action=[BOOL]||[CAST]||[CASTINSTEAD]}`

**Requires [OpenTerrainGenerator](https://github.com/PG85/OpenTerrainGenerator)**

