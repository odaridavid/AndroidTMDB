## AndroidTMDB App

This is a simple Android app that uses the [TMDB API](https://www.themoviedb.org/documentation/api) to display a list of latest movies and their details.

*Pre-requisites*
- Built on A.S Hedgehog 2023.1.1 Canary 7
- JDK 17
- Api key from TMDB. See [here](https://developers.themoviedb.org/3/getting-started/introduction) for more details.Once received
place it in the local.properties file as follows:
``` properties
TMDB_KEY=YOUR_API_KEY
```

### Architecture

### Testing

### Libraries

### Extras


### Todos

- Implement a search feature.
- Implement a favorites feature.
- Setup a CI/CD pipeline with release & debug builds, code coverage, linting, and static analysis
- Implement local caching of data using Room.
- Animation of the UI.


### Screenshots(Light Theme)

| Movie List | Movie Details |
|------------|---------------|
|![Movie List](screenshots/movie_list.png)|![Movie Details](screenshots/movie_details.png)|

### Screenshots(Dark Theme)

| Movie List                                     | Movie Details                                        |
|------------------------------------------------|------------------------------------------------------|
| ![Movie List](screenshots/movie_list_dark.png) | ![Movie Details](screenshots/movie_details_dark.png) |



### LICENSE

```
   Copyright 2023 David Odari

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
   
```
