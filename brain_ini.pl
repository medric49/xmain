go :- hypothesis(Probleme), write('Il faut...'), write(Probleme), nl, undo.

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
hypothesis("bien placer les barrettes RAM, les changer une à une si le problème persiste") :- ram, !.
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
disquedur :- verify("s'allume"), not(verify("finit le chargement du système d'exploitation(ne tourne pas indéfiniment)")).
disquedurmalplace :- verify("s'allume"), verify("affiche le message d'erreur boot device not found").
clavier :- verify("s'allume"), not(verify("affiche les touches que nous saisissons au clavier")).
alimentation :- not(verify("s'allume")), not(verify("s'allume après avoir bien connecté le bouton d'allumage ou après l'avoir changé")).
boutondallumage :- not(verify("s'allume")).

/*Comment poser les questions*/
ask(Question) :- write('Est-ce que la machine '), write(Question),
    write('?'), read(Response), nl,
    (   (Response==yes ; Response==y)
     ->  assert(yes(Question));
	(Response==exit ; Response==ex)
     ->  assert(no(_));
    assert(no(Question)), fail).
:- dynamic yes/1, no/1.

/*Comment vérifier*/
verify(S) :- (yes(S) -> true ; (no(S) -> fail ; ask(S))).

/* undo all yes/no assertions */
undo :- retract(yes(_)),fail.
undo :- retract(no(_)),fail.
undo.
