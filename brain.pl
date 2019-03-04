go :- hypothesis(Probleme), sendSolution(Probleme), nl, undo.

sendSolution(Solution) :- term_to_atom(Solution, S),
		   jpl_call('brain.Engine', handleRep, [S], _).

/*Hypothèses à tester */
hypothesis("changer de chargeur") :- chargeur, !.
hypothesis("changer le circuit d'alimentation") :- alimentation, !.
hypothesis("bien connecter le bouton d'allumage ou le changer") :- boutondallumage, !.
hypothesis("bien connecter le processeur et le ventilateur, les changer si le problème persiste") :- processeur, !.
hypothesis("nettoyer le ventilateur et si cela ne fonctionne pas en changer") :- ventilateur, !.
hypothesis("bien régler la luminosité de l'écran") :- luminosite, !.
hypothesis("changer l'écran") :- ecran, !.
hypothesis("réchauffer la carte graphique, la changer si le problème persiste") :- cartegraphique, !.
hypothesis("refaire le système d'exploitation, si le problème persiste changer de disque dur") :- disquedur, !.
hypothesis("placer correctement le disque dur") :- disquedurmalplace, !.
hypothesis("qsdqzdqzd") :- souris, !.
hypothesis("nettoyer les barrettes RAM, les changer si le problème persiste") :- ram2, !.
hypothesis("vérifier la nappe de connexion du clavier, ou alors utiliser un clavier externe") :- clavier, !.
hypothesis(".....nous ne parvenons pas à détecter votre problème"). /*Pas de diagnostic*/

/*Règles de dépannage*/
ventilateur :- verify("s'allume"), verify("s'arrête juste après").
chargeur :- not(verify("s'allume")), verify("s'allume après avoir changé de chargeur").
processeur :- not(verify("s'allume")), verify("émet 5 bips longs").
luminosite :- verify("s'allume"), verify("a l'écran tout noir ou qui présente des taches"), verify("donne des images après avoir bien réglé la luminosité").
ecran :- verify("s'allume"), not(verify("s'arrête juste après")), verify("a l'écran tout noir ou qui présente des taches"), not(verify("donne des images après avoir bien réglé la luminosité")), verify("donne des images sur un moniteur externe").
ram :- verify("s'allume"), verify("émet 1 bip long suivi d'1 bip court ou alors des bips longs uniquement").
cartegraphique:- (verify("s'allume"), verify("a l'écran tout noir ou qui présente des taches"), not(verify("donne des images sur un moniteur externe")), not(verify("donne des images claires après avoir nettoyé ou changer les RAM"))) ; (verify("s'allume") , verify("émet 1 bip long suivi de 2 bips courts")).
ram2 :- verify("s'allume"), verify("a l'écran tout noir ou qui présente des taches"), not(verify("donne des images sur un moniteur externe")), verify("donne des images claires après avoir nettoyé ou changer les RAM").
disquedurmalplace :- verify("s'allume"), verify("affiche le message d'erreur boot device not found").
clavier :- verify("QAZAZ?").
souris :- verify("sdqsd").
azaz :- verify("sdsdq").
boutondallumage :- not(verify("s'allume")).

/*Comment poser les questions*/
ask(Question) :- term_to_atom(Question, Q),
		   jpl_call('brain.Engine', handle, [Q], _),
           jpl_get('java.lang.System', 'in', In),
           jpl_get('java.lang.System', 'out', Out),
           jpl_new('java.util.Scanner', [In], Sc),
           jpl_call(Sc, next, [], K),
           (  (K==oui ; K==y)
             ->  assert(oui(Question));
             assert(non(Question)), fail).

:- dynamic oui/1, non/1.

/*Comment vérifier*/
verify(S) :- (oui(S) -> true ; (non(S) -> fail ; ask(S))).

/* undo all oui/no assertions */
undo :- retract(oui(_)),fail.
undo :- retract(non(_)),fail.
undo.
