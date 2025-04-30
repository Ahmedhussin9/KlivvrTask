#  Klivvr City Search – Jetpack Compose

A fast, modern city search app built for performance, clarity, and UI polish.  
Features instant prefix search across 200,000+ cities using a custom Trie, with beautiful grouping, sticky headers, timeline UI, and real-time responsiveness.
##  Features

-  Ultra-fast **prefix search** using a Trie
-  Grouped A–Z list with **sticky headers**
-  Custom **timeline UI** with vertical Canvas dividers
-  Open city location in **Google Maps**
-  Flags supported
-  **Screen rotation** supported
-  Empty & loading **animated states**
-  Clean UI built fully in **Jetpack Compose**
-  Compatible with Android **5.0+**

##  Architecture

**MVVM** + Jetpack principles:
- `SearchViewModel`: handles search state and triggers
- `CityRepository`: loads JSON, builds optimized Trie
- `CityTrie`: custom in-memory search structure
- `SearchScreen`: single-source-of-truth UI driven by `uiState`

>  Decoupled, modular, scalable, and testable architecture

##  Trie-based Search Optimization

Instead of filtering a huge list linearly, the app uses a **Trie** to store city names, providing:

- **O(k)** search time where `k = prefix length`
- Case-insensitive lookups
- Instant updates as the user types
- Clean memory use with efficient structure

This results in **sub-linear** runtime and smooth UI even on older Android devices.

---
##  UI & UX Decisions

-  Sticky A–Z headers with clean spacing
-  Vertical **timeline** (custom `Canvas`) with section circles
-  Country code in rounded chips
-  Smooth empty/loading transitions
-  Tap a city → opens in **Google Maps**
-  Matches the provided design prototype with enhancements

##  Compatibility & Libraries

| Feature | Library Used |
|--------|--------------|

| JSON parsing | `kotlinx.serialization` |
| Dependency Injection | `Hilt` |
| UI toolkit | Jetpack Compose |
| Min SDK | API 21 (Android 5.0) |

![IMG-20250430-WA0017](https://github.com/user-attachments/assets/6cb995ae-de5d-44a8-91ef-36004443fe3d)
![IMG-20250430-WA0018](https://github.com/user-attachments/assets/b0c845f7-a160-4c56-b2c1-610efe7cda72)
![IMG-20250430-WA0019](https://github.com/user-attachments/assets/ae852cfa-a4a9-4207-8d69-b6a4bcffd5d3)
![IMG-20250430-WA0020](https://github.com/user-attachments/assets/74a414f1-1fad-41ff-993d-0aaf682cdce2)
