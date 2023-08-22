# Pokedex java boilerplate

# Introduction

Cet exercice consiste à implémenter le serveur back-end des pokédexs de Kanto.

Il s'agit d'un serveur REST dont chacun des endpoints est décrit dans les users stories ci-dessous.

Chaque user story rapporte un certain nombre de points. Si les tests automatisés associés à cette user story fonctionnent, ces points vous seront accordés. Si une partie d'entres eux fonctionnent mais pas tous, vous marquerez des points au prorata des tests passés.

Par ailleurs 5 points seront réservés à la qualité du code implémenté selon les critères suivants:
- Architecture de l'application
- Maintenabilité du code
- Pertinence des choix des containers
- Threadsafety
- Gestion des exceptions

| User story    | Points |
| ------------- | ---- |
| User Story 1 | 4 |
| User Story 2 | 4 |
| User Story 3 | 3 |
| User Story 4 | 2 |
| User Story 5 | 2 |

Le boiler plate associé à ce README vous est fourni comme point de départ avec un endpoint `/api/status` qui ne doit pas être modifié.

Les modèles ont été pré-implémentés pour vous faire gagner du temps, votre rôle sera d'implémenter les endpoints REST pour répondre aux fonctionnalitées décrites dans les US ci-dessous.


# Description de l'application

Un pokédex est une console qui permet de consulter les caractéristiques d'un pokémon. On pourra ainsi ajouter et supprimer des pokémons, puis consulter les caractérisques d'un ou de plusieurs pokémons.
Les modèles de données utilisés pour cet exercice sont présentés ci-dessous.

## Pokémon

| Nom de l'attribut | Type |
| ------------- | ------------ |
| name | String |
| type | Elements |
|  lifePoints | int |
| powers | List\<Power> |

## Elements
Les différents éléments sont:
- neutral
- fire
- water
- grass
- electric
- ice
- fighting
- poison
- ground

## Power
| Nom de l'attribut | Type |
| ------------- | ------------ |
| name | String |
| damageType | Elements |
| damage | int |

# US 1 - Création d'un pokémon

En tant qu'utilisateur, je souhaite ajouter un pokémon dans la base du Pokédex. J'envoie une requête Json de type `POST` contenant les informations nécessaires à la création.
- Le nom
- Le type
- Le nombre de points de vie
- La liste des capacités

Si le pokémon existe déjà ou que le json est incomplet ou invalide, une erreur est renvoyée par le serveur.

En cas de réussite, une requête vide est envoyée par le serveur avec le code d'erreur 200.
En cas d'échec, une requête vide est envoyée par le serveur avec le code d'erreur 400.

Le endpoint à utiliser est `/api/create`

## Spécifications d'interfaces
### Requête

```json
{
    "name": "Pikachu",
    "type": "Electric",
    "lifePoints": 70,
    "powers": [
        {
            "name": "gnaw",
            "powerType": "neutral",
            "damage": 30
        }
    ]
}
```

# US 2 - Recherche de pokémons par nom

En tant qu'utilisateur, je souhaite récupérer une liste de pokémons correspondants à certains critères. J'envoie une requète de type `GET` sur le endpoint `/api/searchByName?name=nameToSearch` ayant pour paramètre `name` contenant la chaine de caractère à rechercher.

Le serveur doit envoyer la liste des pokémons pour lesquelles la chaine de caractères fournie par l'utilisateur **est contenue** dans le nom du pokémon.

Si aucun pokémon ne correspond à cette chaine, une liste vide est renvoyée.

Si le paramètre est invalide (par exemple de mauvais type), le serveur envoie une requête vide avec le code d'erreur 400.

Exemple de requête : `/api/searchByName?name=Pika`

## Spécifications d'interfaces
### Réponse

```json
{
    [
        {
            "name": "Pikachu",
            "lifePoints": 80,
            "powers": [
                {
                    "name": "gnaw",
                    "powerType": "neutral",
                    "damage": 30
                }
                {
                    "name": "thunder jolt",
                    "powerType": "electric",
                    "damage": 50
                }
            ]
        }
    ]
}
```


# US 3 - Modification d'un pokémon

En tant qu'utilisateur, je souhaite modifier un pokémon dans la base du Pokédex. J'envoie une requête Json de type `POST` contenant le nom du pokémon et les informations à modifier. Tous les attributs d'un pokémon peuvent être modifiés sauf le nom.

En ce qui concerne la liste de capacité, si la capacité existe déjà elle est modifiée, sinon une nouvelle capacité sera créée.

Si le pokémon n'existe pas une erreur 404 avec un message vide est renvoyée par le serveur.

Le endpoint à utiliser est `/api/modify`

## Spécifications d'interfaces
### Requête example 1

```json
{
    "name": "Pikachu",
    "lifePoints": 80
}
```

### Requête example 2

```json
{
    "name": "Pikachu",
    "powers": [
        {
            "name": "thunder jolt",
            "powerType": "electric",
            "damage": 50
        }
    ]
}
```


# US 4 - Recherche de pokémons par type

En tant qu'utilisateur, je souhaite récupérer une liste de pokémons appartenant au même type. J'envoie une requète de type `GET` ayant pour paramètre le `type` contenant le type à rechercher.

Le serveur doit envoyer la liste des pokémons qui sont du type recherché.

Si aucun pokémon ne correspond à cette chaine, une liste vide est renvoyée.

Si le type recherché n'ai pas dans la liste de type possible, le serveur renvoie une requête vide avec le code d'erreur 400.

Exemple de requête : `/api/searchByType?type=electric`

## Spécifications d'interfaces

### Réponse
```json
{
    [
        {
            "name": "Pikachu",
            "lifePoints": 80,
            "powers": [
                {
                    "name": "gnaw",
                    "powerType": "neutral",
                    "damage": 30
                }
                {
                    "name": "thunder jolt",
                    "powerType": "electric",
                    "damage": 50
                }
            ]
        }
    ]
}
```


# US 5 - Ajout des évolutions

TODO après un premier test en fonction du timing

Gestion des évolutions de pokémon + recherche de pokémons par arbre d'évolution


