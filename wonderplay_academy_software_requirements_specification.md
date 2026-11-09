# Software Requirements Specification (SRS)
## WonderPlay Academy: Early-Childhood Mobile Learning Application

---

### 1. Executive Summary & Vision
**WonderPlay Academy** is a multi-sensory, early-childhood educational application engineered for children aged 2 through 7 (Phases 1–3). The platform bridges structured developmental milestones with joyful, distraction-free gamified exploration. 

The core philosophy revolves around:
- **Low Cognitive Load & Anti-Glare Visuals**: Utilizing warm, eye-soothing palettes (Warm Oatmeal `#F9F8F3`, pastel undertones), literacy-first typography (**Andika**), and tactile 3D interactions.
- **Natural Multi-Sensory Phonics & Singing**: Real-time Web Audio API acoustic chime synthesis, woman educator voice narration, and native speech synthesis (supporting English and Tamil *Uyir Ezhuthukkal*).
- **Strict Child-Safety Sandboxing**: Built-in COPPA compliance, parental math gates, zero external ad trackers, and an automatic eye-rest screen-time governor.

---

### 2. Pedagogical Framework & Curriculum

The curriculum follows NAEYC (National Association for the Education of Young Children) and Early Literacy developmental guidelines divided into three progressive phases:

```
+-----------------------------------------------------------------------------------+
|                            WONDERPLAY CURRICULUM PIPELINE                        |
+-----------------------------------------------------------------------------------+
|  Phase 1: Explorers (2–3 yrs)  |  Phase 2: Builders (4–5 yrs)   |  Phase 3: Discoverers (6–7 yrs)  |
+--------------------------------+--------------------------------+----------------------------------+
| • Letters A-Z + Phonics cues   | • CVC Word Families (CAT, SUN) | • Digraphs (SH, CH, TH, WH)      |
| • Tamil Uyir Ezhuthukkal (அ-ஃ) | • Community Helpers & Places   | • Earth & Marine Ecosystems      |
| • Numerals (1-10) & Counting   | • Emotional Literacy (SEL)     | • Solar System & Planetary Sci   |
| • Primary Shapes & Colors      | • Animal Habitats & Ecology    | • Time, Days & Weather Cycles    |
| • Sensory Body Parts & Senses  | • Pre-Math Comparison Puzzles  | • Social-Emotional Complex Tales |
+--------------------------------+--------------------------------+----------------------------------+
```

#### 2.1. Phase 1: Sensory Explorers (Ages 2–3)
- **Alphabet & Phonics (A to Z)**: Full 26 uppercase/lowercase foundation anchors with contextual words and audible phonemic cues (e.g., `A for Apple - Crisp, red, and juicy`).
- **Tamil Uyir Ezhuthukkal (தமிழ் உயிர் எழுத்துக்கள்)**: 12 vowels (*அ* through *ஔ*) plus *Aayutha Ezhuthu* (*ஃ*) with native `ta-IN` pronunciation and cultural semantic anchors (*அம்மா*, *ஆடு*, *இலை*, etc.).
- **Dual Number Decks**:
  - *Numerals (1–10)*: Clean symbol recognition.
  - *Counting Explorer*: Concrete item counters with tactile emoji grouping (*1 Sun, 2 Eyes, 3 Birds, ..., 10 Toes*).
- **Shapes & Colors**: Foundational 2D geometries (Circle, Square, Triangle, Star, Heart, Diamond) paired with high-contrast pastel colors.
- **Body & Senses**: Auditory and visual recognition of eyes, ears, nose, and hands.

#### 2.2. Phase 2: Word Builders (Ages 4–5)
- **CVC Phonemic Blending**: Short vowel word families (*-at, -un, -og, -us, -ig, -en*) broken down into onset and rime.
- **Community Helpers**: Understanding civic environments (*Firefighters, Doctors, Teachers, Police Officers, Farmers*).
- **Feelings & Emotions (SEL)**: Identifying emotional states (*Happy, Calm, Curious, Brave*).
- **Animal Classifications**: Farm, savanna, and marine biology associations.

#### 2.3. Phase 3: Discoverers (Ages 6–7)
- **Digraph Phonics**: Complex blended letter sounds (*SH, CH, TH, WH*).
- **Earth Science & Ecosystems**: Rainforests, Coral Reefs, Deserts, and Alpine biomes.
- **Space & Solar System**: Sun, Earth, Moon, Mars, and orbital physics at a foundational level.
- **Time, Seasons & Cycles**: Spring, Summer, Autumn, Winter transitions and daily temporal sequences.

---

### 3. Functional Requirements

#### 3.1. Unified Navigation Model (Two-Tier Pattern)
To maintain low cognitive friction, every major learning section (Flashcards, Stories, Games, Melody, Rhymes) must follow a consistent two-tier interaction model:
1. **Tier 1 (Catalog Hub)**: 
   - A grid of tactile cards/tiles presenting titles, item counters, and transparent vector graphics.
   - Global status bar, profile HUD, and bottom dock are visible.
2. **Tier 2 (Full-Screen Immersive Canvas)**:
   - Triggered upon tapping any catalog tile.
   - Automatically hides the top header and bottom dock navigation to prevent accidental touches.
   - Contains a prominent `← Back` button at top-left to return to Tier 1.
   - Includes edge-to-edge content, gesture swiping, and directional previous/next controls.

#### 3.2. Audio & Speech Synthesis Engine
- **Educator Voice Calibration**:
  - Speech synthesis must target female voices (e.g., Samantha, Victoria, Zira, Google US English Female).
  - Narration speed must be capped at a calm, early-childhood rate: **0.68x to 0.72x** with a pitch multiplier of **1.10x to 1.15x**.
  - Technical phonetic strings (e.g., `/æ/`, `/b/`) must not be spoken aloud; the system must read natural, complete pedagogical sentences.
- **Native Tamil Engine**:
  - The engine must query the browser voice list for `ta-IN` or language codes starting with `ta`.
  - When viewing Tamil content, sentences must be parsed and spoken in native Tamil (e.g., *"அம்மா! அன்பான அம்மா"*).
- **Acoustic Web Audio API Synthesizer**:
  - Xylophone and chime audio must use harmonic dual-oscillator waveforms (Sine foundational + Triangle 2nd harmonic) with exponential decay to create natural, non-jarring bell acoustics.
  - Sound effects: Gentle sine pop (80ms), melodic fanfare, and diatonic chime tones (261.63 Hz to 523.25 Hz).

#### 3.3. Module Specifications

##### A. Flashcards & Gesture Navigation
- Displays large visual letter/number graphics using the literacy-focused **Andika** font.
- Bidirectional gesture detection:
  - Swipe left: Advances to the next card; if at end of deck, wraps to next category with confetti celebration.
  - Swipe right: Returns to previous card; if at start, wraps to previous category.
  - Tap card: Re-triggers calm educator voiceover.

##### B. Read-Along Stories & Karaoke Tracker
- Displays multi-page illustrated narratives with morals centered on empathy, courage, and growth.
- **Karaoke Word Highlighting**: As speech plays, the `onboundary` event synchronizes word indices, highlighting each spoken word with an amber pill in real-time.
- Interactive word-tap: Tapping any word in the sentence pronounces that single word.

##### C. Melody Studio & Play-Along Guide
- **Play-Along Prompter**: Interactive sequence prompter displaying song notes (*Twinkle Little Star*, *Mary's Lamb*, *Old MacDonald*, *Row Your Boat*).
- Highlights keys in real-time as notes are played and automatically loops through the song.
- **Rainbow Chime Bars**: 8-key diatonic physical chime bars (*C, D, E, F, G, A, B, C²*) with touch scaling and tactile feedback.

##### D. Rhymes Sing-Along Engine
- Synchronous melodic singing synthesizer: Rather than reciting prose, the app maps each syllable to a diatonic musical frequency and pitch multiplier.
- Dual-track playback: Sustained bell chime note played simultaneously with the spoken syllable.
- Support for classic rhymes and regional lullabies (*நிலா நிலா ஓடி வா*).

##### E. Games & Logic Puzzles
- **Discovery Quiz**: Multiple-choice auditory questions with immediate positive reinforcement (star rewards, confetti, non-punitive re-try cues).
- **Balloon Letter Pop**: Sensory-motor coordination game allowing kids to pop floating letter balloons to hear phoneme sounds.

##### F. Daily Routine Habits
- Interactive checklist covering hygiene and prosocial behavior (teeth brushing, hand washing, hydration, room tidying).
- Toggling habits rewards stars with audio encouragement.

---

### 4. UI/UX Design System & Theme Engine

#### 4.1. Visual Ergonomics & Typography
- **Primary Typeface**: **Andika** (Google Fonts). Specifically designed for early literacy to eliminate letter confusion (distinct strokes for lowercase 'a', 'l', and number '1').
- **Canvas Base Color**: Warm Oatmeal (`#F9F8F3`), eliminating high-contrast screen glare.
- **Button Standards**: Tactile 3D buttons with a `4px` drop-shadow, active `scale(0.95)` depression, and minimum touch target size of **48 × 48 px**.

#### 4.2. Organic Theme Skins
The application features 6 switchable, pastel-toned worlds that tint the status bar, headers, card gradients, and navigation docks:

| Theme ID | World Name | Primary Motifs | Palette Hex / Gradient |
| :--- | :--- | :--- | :--- |
| `safari` | Jungle Safari | Lions, savanna, warm honey | `#F9F8F3` / Amber-50 to Oatmeal |
| `dino` | Prehistoric Dinos | T-Rex, fossils, moss foliage | `#F4F7F4` / Emerald-50 to Mint |
| `ocean` | Under the Sea | Whales, coral reef, sea turtles | `#F2F7F9` / Sky-50 to Marine Mist |
| `space` | Space Voyage | Moon, stars, friendly rockets | `#F2F1F8` / Starlight Lilac |
| `fairytale`| Enchanted Fairytale| Castles, unicorns, gentle dragons| `#F7F1F4` / Soft Blossom Rose |
| `vehicles` | Vehicles & Build | Trains, dump trucks, tracks | `#F6F2EB` / Warm Clay & Linen |

---

### 5. Child Safety, COPPA Compliance & Parental Controls

```
+-----------------------------------------------------------------------------------+
|                           PARENTAL GATE & SAFETY PIPELINE                         |
+-----------------------------------------------------------------------------------+
|                                                                                   |
|  [ Tap 'Adults' Button ]                                                          |
|            │                                                                      |
|            ▼                                                                      |
|  [ Arithmetic Challenge Modal ]  ───( Incorrect Answer )──► [ Shakes / Retries ]  |
|            │                                                                      |
|     ( Correct Answer )                                                            |
|            ▼                                                                      |
|  [ Unlock Parent Control Console ]                                                |
|       ├─► 1. Screen-Time Eye Rest Governor (15m, 20m, 30m, Off)                   |
|       ├─► 2. COPPA Zero-Tracker Verification (No external scripts)                |
|       ├─► 3. Curriculum Milestone Progress Viewer                                 |
|       └─► 4. Theme Locking & Sensory Customization                                |
+-----------------------------------------------------------------------------------+
```

#### 5.1. COPPA & Privacy Rules
1. **Zero Tracking**: Zero third-party ad networks, tracking pixels, cookies, or profiling scripts.
2. **Local Data Persistence**: Child profiles, nicknames, star counts, and habit progress are stored strictly in-memory or on local device storage.
3. **No External Outbound Links**: No external hyperlinks accessible by children without passing through the parental gate.

#### 5.2. Parental Gate (Adult Verification)
- Access to configuration options requires solving a dynamically generated arithmetic challenge (e.g., $7 + 8 = ?$) presented inside a bottom-sheet modal.
- Failed attempts show gentle error cues without locking parents out permanently.

#### 5.3. Screen-Time Eye Rest Governor
- Built-in timer tracks continuous active session time (default: **15 minutes**; configurable to 20m, 30m, or Off).
- Once the threshold is reached:
  - Halts audio synthesis immediately.
  - Displays a calming full-screen eye-rest overlay (*"Stretch high like a giraffe and sip some cool water"*).
  - Imposes a mandatory **20-second relaxation countdown** before the "Resume Playing" button can be tapped.

---

### 6. Technical Stack & Architecture

- **Runtime Environment**: Standard Webview / Browser (iOS Safari WebKit & Android Chrome WebView).
- **Layout & Styling**: Tailwind CSS with custom theme extensions and haptic keyframe animations.
- **Font Rendering**: Google Web Fonts (`Andika:wght@400;700`).
- **Iconography**: FontAwesome 6 Free SVG/Font vectors.
- **Audio Engine**: 
  - W3C **Web Audio API** (`AudioContext`, `OscillatorNode`, `GainNode`).
  - W3C **Web Speech API** (`SpeechSynthesis`, `SpeechSynthesisUtterance`).
- **Particle System**: Canvas-Confetti browser bundle.

---

### 7. Non-Functional Requirements & Performance Metrics

1. **Responsiveness**: Instantaneous touch-to-sound latency ($\le 40\text{ ms}$) via pre-warmed `AudioContext`.
2. **Frame Budget**: Fluid 60 FPS transitions across all touch interactions, card flips, and modal slide-ins.
3. **Offline First**: All audio synthesis and curriculum logic are self-contained and run without an active internet connection (relying on built-in OS speech voices).
4. **Memory Footprint**: Strict memory limits ($\le 60\text{ MB}$) preventing browser crashes on low-end tablets and mobile devices.