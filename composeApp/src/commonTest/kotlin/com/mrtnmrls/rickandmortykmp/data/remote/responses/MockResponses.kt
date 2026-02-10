package com.mrtnmrls.rickandmortykmp.data.remote.responses

object MockResponses {
    val GET_CHARACTERS_RESPONSE = """
        {
           "info":{
              "count":826,
              "pages":42,
              "next":"https://rickandmortyapi.com/api/character?page=2",
              "prev":null
           },
           "results":[
              {
                 "id":1,
                 "name":"Rick Sanchez",
                 "status":"Alive",
                 "species":"Human",
                 "type":"",
                 "gender":"Male",
                 "origin":{
                    "name":"Earth (C-137)",
                    "url":"https://rickandmortyapi.com/api/location/1"
                 },
                 "location":{
                    "name":"Citadel of Ricks",
                    "url":"https://rickandmortyapi.com/api/location/3"
                 },
                 "image":"https://rickandmortyapi.com/api/character/avatar/1.jpeg",
                 "episode":[
                    "https://rickandmortyapi.com/api/episode/1",
                    "https://rickandmortyapi.com/api/episode/2",
                    "https://rickandmortyapi.com/api/episode/3",
                    "https://rickandmortyapi.com/api/episode/4",
                    "https://rickandmortyapi.com/api/episode/5",
                    "https://rickandmortyapi.com/api/episode/6",
                    "https://rickandmortyapi.com/api/episode/7",
                    "https://rickandmortyapi.com/api/episode/8",
                    "https://rickandmortyapi.com/api/episode/9",
                    "https://rickandmortyapi.com/api/episode/10",
                    "https://rickandmortyapi.com/api/episode/11",
                    "https://rickandmortyapi.com/api/episode/12",
                    "https://rickandmortyapi.com/api/episode/13",
                    "https://rickandmortyapi.com/api/episode/14",
                    "https://rickandmortyapi.com/api/episode/15",
                    "https://rickandmortyapi.com/api/episode/16",
                    "https://rickandmortyapi.com/api/episode/17",
                    "https://rickandmortyapi.com/api/episode/18",
                    "https://rickandmortyapi.com/api/episode/19",
                    "https://rickandmortyapi.com/api/episode/20",
                    "https://rickandmortyapi.com/api/episode/21",
                    "https://rickandmortyapi.com/api/episode/22",
                    "https://rickandmortyapi.com/api/episode/23",
                    "https://rickandmortyapi.com/api/episode/24",
                    "https://rickandmortyapi.com/api/episode/25",
                    "https://rickandmortyapi.com/api/episode/26",
                    "https://rickandmortyapi.com/api/episode/27",
                    "https://rickandmortyapi.com/api/episode/28",
                    "https://rickandmortyapi.com/api/episode/29",
                    "https://rickandmortyapi.com/api/episode/30",
                    "https://rickandmortyapi.com/api/episode/31",
                    "https://rickandmortyapi.com/api/episode/32",
                    "https://rickandmortyapi.com/api/episode/33",
                    "https://rickandmortyapi.com/api/episode/34",
                    "https://rickandmortyapi.com/api/episode/35",
                    "https://rickandmortyapi.com/api/episode/36",
                    "https://rickandmortyapi.com/api/episode/37",
                    "https://rickandmortyapi.com/api/episode/38",
                    "https://rickandmortyapi.com/api/episode/39",
                    "https://rickandmortyapi.com/api/episode/40",
                    "https://rickandmortyapi.com/api/episode/41",
                    "https://rickandmortyapi.com/api/episode/42",
                    "https://rickandmortyapi.com/api/episode/43",
                    "https://rickandmortyapi.com/api/episode/44",
                    "https://rickandmortyapi.com/api/episode/45",
                    "https://rickandmortyapi.com/api/episode/46",
                    "https://rickandmortyapi.com/api/episode/47",
                    "https://rickandmortyapi.com/api/episode/48",
                    "https://rickandmortyapi.com/api/episode/49",
                    "https://rickandmortyapi.com/api/episode/50",
                    "https://rickandmortyapi.com/api/episode/51"
                 ],
                 "url":"https://rickandmortyapi.com/api/character/1",
                 "created":"2017-11-04T18:48:46.250Z"
              },
              {
                 "id":2,
                 "name":"Morty Smith",
                 "status":"Alive",
                 "species":"Human",
                 "type":"",
                 "gender":"Male",
                 "origin":{
                    "name":"unknown",
                    "url":""
                 },
                 "location":{
                    "name":"Citadel of Ricks",
                    "url":"https://rickandmortyapi.com/api/location/3"
                 },
                 "image":"https://rickandmortyapi.com/api/character/avatar/2.jpeg",
                 "episode":[
                    "https://rickandmortyapi.com/api/episode/1",
                    "https://rickandmortyapi.com/api/episode/2",
                    "https://rickandmortyapi.com/api/episode/3",
                    "https://rickandmortyapi.com/api/episode/4",
                    "https://rickandmortyapi.com/api/episode/5",
                    "https://rickandmortyapi.com/api/episode/6",
                    "https://rickandmortyapi.com/api/episode/7",
                    "https://rickandmortyapi.com/api/episode/8",
                    "https://rickandmortyapi.com/api/episode/9",
                    "https://rickandmortyapi.com/api/episode/10",
                    "https://rickandmortyapi.com/api/episode/11",
                    "https://rickandmortyapi.com/api/episode/12",
                    "https://rickandmortyapi.com/api/episode/13",
                    "https://rickandmortyapi.com/api/episode/14",
                    "https://rickandmortyapi.com/api/episode/15",
                    "https://rickandmortyapi.com/api/episode/16",
                    "https://rickandmortyapi.com/api/episode/17",
                    "https://rickandmortyapi.com/api/episode/18",
                    "https://rickandmortyapi.com/api/episode/19",
                    "https://rickandmortyapi.com/api/episode/20",
                    "https://rickandmortyapi.com/api/episode/21",
                    "https://rickandmortyapi.com/api/episode/22",
                    "https://rickandmortyapi.com/api/episode/23",
                    "https://rickandmortyapi.com/api/episode/24",
                    "https://rickandmortyapi.com/api/episode/25",
                    "https://rickandmortyapi.com/api/episode/26",
                    "https://rickandmortyapi.com/api/episode/27",
                    "https://rickandmortyapi.com/api/episode/28",
                    "https://rickandmortyapi.com/api/episode/29",
                    "https://rickandmortyapi.com/api/episode/30",
                    "https://rickandmortyapi.com/api/episode/31",
                    "https://rickandmortyapi.com/api/episode/32",
                    "https://rickandmortyapi.com/api/episode/33",
                    "https://rickandmortyapi.com/api/episode/34",
                    "https://rickandmortyapi.com/api/episode/35",
                    "https://rickandmortyapi.com/api/episode/36",
                    "https://rickandmortyapi.com/api/episode/37",
                    "https://rickandmortyapi.com/api/episode/38",
                    "https://rickandmortyapi.com/api/episode/39",
                    "https://rickandmortyapi.com/api/episode/40",
                    "https://rickandmortyapi.com/api/episode/41",
                    "https://rickandmortyapi.com/api/episode/42",
                    "https://rickandmortyapi.com/api/episode/43",
                    "https://rickandmortyapi.com/api/episode/44",
                    "https://rickandmortyapi.com/api/episode/45",
                    "https://rickandmortyapi.com/api/episode/46",
                    "https://rickandmortyapi.com/api/episode/47",
                    "https://rickandmortyapi.com/api/episode/48",
                    "https://rickandmortyapi.com/api/episode/49",
                    "https://rickandmortyapi.com/api/episode/50",
                    "https://rickandmortyapi.com/api/episode/51"
                 ],
                 "url":"https://rickandmortyapi.com/api/character/2",
                 "created":"2017-11-04T18:50:21.651Z"
              },
              {
                 "id":3,
                 "name":"Summer Smith",
                 "status":"Alive",
                 "species":"Human",
                 "type":"",
                 "gender":"Female",
                 "origin":{
                    "name":"Earth (Replacement Dimension)",
                    "url":"https://rickandmortyapi.com/api/location/20"
                 },
                 "location":{
                    "name":"Earth (Replacement Dimension)",
                    "url":"https://rickandmortyapi.com/api/location/20"
                 },
                 "image":"https://rickandmortyapi.com/api/character/avatar/3.jpeg",
                 "episode":[
                    "https://rickandmortyapi.com/api/episode/6",
                    "https://rickandmortyapi.com/api/episode/7",
                    "https://rickandmortyapi.com/api/episode/8",
                    "https://rickandmortyapi.com/api/episode/9",
                    "https://rickandmortyapi.com/api/episode/10",
                    "https://rickandmortyapi.com/api/episode/11",
                    "https://rickandmortyapi.com/api/episode/12",
                    "https://rickandmortyapi.com/api/episode/14",
                    "https://rickandmortyapi.com/api/episode/15",
                    "https://rickandmortyapi.com/api/episode/16",
                    "https://rickandmortyapi.com/api/episode/17",
                    "https://rickandmortyapi.com/api/episode/18",
                    "https://rickandmortyapi.com/api/episode/19",
                    "https://rickandmortyapi.com/api/episode/20",
                    "https://rickandmortyapi.com/api/episode/21",
                    "https://rickandmortyapi.com/api/episode/22",
                    "https://rickandmortyapi.com/api/episode/23",
                    "https://rickandmortyapi.com/api/episode/24",
                    "https://rickandmortyapi.com/api/episode/25",
                    "https://rickandmortyapi.com/api/episode/26",
                    "https://rickandmortyapi.com/api/episode/27",
                    "https://rickandmortyapi.com/api/episode/29",
                    "https://rickandmortyapi.com/api/episode/30",
                    "https://rickandmortyapi.com/api/episode/31",
                    "https://rickandmortyapi.com/api/episode/32",
                    "https://rickandmortyapi.com/api/episode/33",
                    "https://rickandmortyapi.com/api/episode/34",
                    "https://rickandmortyapi.com/api/episode/35",
                    "https://rickandmortyapi.com/api/episode/36",
                    "https://rickandmortyapi.com/api/episode/38",
                    "https://rickandmortyapi.com/api/episode/39",
                    "https://rickandmortyapi.com/api/episode/40",
                    "https://rickandmortyapi.com/api/episode/41",
                    "https://rickandmortyapi.com/api/episode/42",
                    "https://rickandmortyapi.com/api/episode/43",
                    "https://rickandmortyapi.com/api/episode/44",
                    "https://rickandmortyapi.com/api/episode/45",
                    "https://rickandmortyapi.com/api/episode/46",
                    "https://rickandmortyapi.com/api/episode/47",
                    "https://rickandmortyapi.com/api/episode/48",
                    "https://rickandmortyapi.com/api/episode/49",
                    "https://rickandmortyapi.com/api/episode/51"
                 ],
                 "url":"https://rickandmortyapi.com/api/character/3",
                 "created":"2017-11-04T19:09:56.428Z"
              }
           ]
        }
    """.trimIndent()

    val GET_CHARACTER_RESPONSE = """
        {
          "id": 2,
          "name": "Morty Smith",
          "status": "Alive",
          "species": "Human",
          "type": "",
          "gender": "Male",
          "origin": {
            "name": "unknown",
            "url": ""
          },
          "location": {
            "name": "Citadel of Ricks",
            "url": "https://rickandmortyapi.com/api/location/3"
          },
          "image": "https://rickandmortyapi.com/api/character/avatar/2.jpeg",
          "episode": [
            "https://rickandmortyapi.com/api/episode/1",
            "https://rickandmortyapi.com/api/episode/2",
            "https://rickandmortyapi.com/api/episode/3",
            "https://rickandmortyapi.com/api/episode/4",
            "https://rickandmortyapi.com/api/episode/5",
            "https://rickandmortyapi.com/api/episode/6",
            "https://rickandmortyapi.com/api/episode/7",
            "https://rickandmortyapi.com/api/episode/8",
            "https://rickandmortyapi.com/api/episode/9",
            "https://rickandmortyapi.com/api/episode/10",
            "https://rickandmortyapi.com/api/episode/11",
            "https://rickandmortyapi.com/api/episode/12",
            "https://rickandmortyapi.com/api/episode/13",
            "https://rickandmortyapi.com/api/episode/14",
            "https://rickandmortyapi.com/api/episode/15",
            "https://rickandmortyapi.com/api/episode/16",
            "https://rickandmortyapi.com/api/episode/17",
            "https://rickandmortyapi.com/api/episode/18",
            "https://rickandmortyapi.com/api/episode/19",
            "https://rickandmortyapi.com/api/episode/20",
            "https://rickandmortyapi.com/api/episode/21",
            "https://rickandmortyapi.com/api/episode/22",
            "https://rickandmortyapi.com/api/episode/23",
            "https://rickandmortyapi.com/api/episode/24",
            "https://rickandmortyapi.com/api/episode/25",
            "https://rickandmortyapi.com/api/episode/26",
            "https://rickandmortyapi.com/api/episode/27",
            "https://rickandmortyapi.com/api/episode/28",
            "https://rickandmortyapi.com/api/episode/29",
            "https://rickandmortyapi.com/api/episode/30",
            "https://rickandmortyapi.com/api/episode/31",
            "https://rickandmortyapi.com/api/episode/32",
            "https://rickandmortyapi.com/api/episode/33",
            "https://rickandmortyapi.com/api/episode/34",
            "https://rickandmortyapi.com/api/episode/35",
            "https://rickandmortyapi.com/api/episode/36",
            "https://rickandmortyapi.com/api/episode/37",
            "https://rickandmortyapi.com/api/episode/38",
            "https://rickandmortyapi.com/api/episode/39",
            "https://rickandmortyapi.com/api/episode/40",
            "https://rickandmortyapi.com/api/episode/41",
            "https://rickandmortyapi.com/api/episode/42",
            "https://rickandmortyapi.com/api/episode/43",
            "https://rickandmortyapi.com/api/episode/44",
            "https://rickandmortyapi.com/api/episode/45",
            "https://rickandmortyapi.com/api/episode/46",
            "https://rickandmortyapi.com/api/episode/47",
            "https://rickandmortyapi.com/api/episode/48",
            "https://rickandmortyapi.com/api/episode/49",
            "https://rickandmortyapi.com/api/episode/50",
            "https://rickandmortyapi.com/api/episode/51"
          ],
          "url": "https://rickandmortyapi.com/api/character/2",
          "created": "2017-11-04T18:50:21.651Z"
        }
    """.trimIndent()

    val GET_LOCATIONS_RESPONSE = """
        {
           "info":{
              "count":126,
              "pages":7,
              "next":"https://rickandmortyapi.com/api/location?page=2",
              "prev":null
           },
           "results":[
              {
                 "id":1,
                 "name":"Earth (C-137)",
                 "type":"Planet",
                 "dimension":"Dimension C-137",
                 "residents":[
                    "https://rickandmortyapi.com/api/character/38"
                 ],
                 "url":"https://rickandmortyapi.com/api/location/1",
                 "created":"2017-11-10T12:42:04.162Z"
              },
              {
                 "id":2,
                 "name":"Abadango",
                 "type":"Cluster",
                 "dimension":"unknown",
                 "residents":[
                    "https://rickandmortyapi.com/api/character/6"
                 ],
                 "url":"https://rickandmortyapi.com/api/location/2",
                 "created":"2017-11-10T13:06:38.182Z"
              },
              {
                 "id":3,
                 "name":"Citadel of Ricks",
                 "type":"Space station",
                 "dimension":"unknown",
                 "residents":[
                    "https://rickandmortyapi.com/api/character/8",
                    "https://rickandmortyapi.com/api/character/14"
                 ],
                 "url":"https://rickandmortyapi.com/api/location/3",
                 "created":"2017-11-10T13:08:13.191Z"
              }
           ]
        }
    """.trimIndent()

    val GET_EPISODES_RESPONSE = """
        {
           "info":{
              "count":51,
              "pages":3,
              "next":"https://rickandmortyapi.com/api/episode?page=2",
              "prev":null
           },
           "results":[
              {
                 "id":1,
                 "name":"Pilot",
                 "air_date":"December 2, 2013",
                 "episode":"S01E01",
                 "characters":[
                    "https://rickandmortyapi.com/api/character/1"
                 ],
                 "url":"https://rickandmortyapi.com/api/episode/1",
                 "created":"2017-11-10T12:56:33.798Z"
              },
              {
                 "id":2,
                 "name":"Lawnmower Dog",
                 "air_date":"December 9, 2013",
                 "episode":"S01E02",
                 "characters":[
                    "https://rickandmortyapi.com/api/character/1"
                 ],
                 "url":"https://rickandmortyapi.com/api/episode/2",
                 "created":"2017-11-10T12:56:33.916Z"
              },
              {
                 "id":3,
                 "name":"Anatomy Park",
                 "air_date":"December 16, 2013",
                 "episode":"S01E03",
                 "characters":[
                    "https://rickandmortyapi.com/api/character/1"
                 ],
                 "url":"https://rickandmortyapi.com/api/episode/3",
                 "created":"2017-11-10T12:56:34.022Z"
              }
           ]
        }
    """.trimIndent()
}