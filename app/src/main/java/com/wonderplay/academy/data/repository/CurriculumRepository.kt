package com.wonderplay.academy.data.repository

import com.wonderplay.academy.data.model.*

object CurriculumRepository {

    val phases = mapOf(
        "phase1" to CurriculumPhase(
            id = "phase1",
            title = "Age 1–2: Toddlers (Early Sensory & Object Recognition)",
            categories = listOf(
                CurriculumCategory(
                    id = "toddler_words",
                    name = "First Words & Daily Objects",
                    items = listOf(
                        FlashcardItem("Cup", "Drinking Cup", "k-uh-p", "🥤", "Cup for drinking water and milk."),
                        FlashcardItem("Ball", "Play Ball", "b-aw-l", "⚽", "Ball for rolling and playing."),
                        FlashcardItem("Shoes", "Little Shoes", "sh-oo-z", "👟", "Shoes for walking outside."),
                        FlashcardItem("Bed", "Bedtime Bed", "b-eh-d", "🛏️", "Bed for cozy sleeping."),
                        FlashcardItem("Bottle", "Milk Bottle", "b-ot-l", "🍼", "Bottle for milk and water."),
                        FlashcardItem("Spoon", "Feeding Spoon", "s-p-oo-n", "🥄", "Spoon for yummy meals."),
                        FlashcardItem("Car", "Toy Car", "k-ah-r", "🚗", "Car going beep beep.")
                    )
                ),
                CurriculumCategory(
                    id = "toddler_animals",
                    name = "Animals & Sound Matching",
                    items = listOf(
                        FlashcardItem("Cow", "Farm Cow", "k-ow", "🐄", "Cow says Moo Moo!"),
                        FlashcardItem("Dog", "Puppy Dog", "d-o-g", "🐶", "Dog says Woof Woof!"),
                        FlashcardItem("Cat", "Kitty Cat", "k-a-t", "🐱", "Cat says Meow Meow!"),
                        FlashcardItem("Lion", "Zoo Lion", "l-eye-on", "🦁", "Lion says Roar!"),
                        FlashcardItem("Elephant", "Big Elephant", "el-uh-funt", "🐘", "Elephant makes trumpet sounds!")
                    )
                ),
                CurriculumCategory(
                    id = "toddler_colors",
                    name = "Basic Colors",
                    items = listOf(
                        FlashcardItem("Red", "Red Color", "r-eh-d", "🔴", "Bright Red like a strawberry."),
                        FlashcardItem("Blue", "Blue Color", "b-l-oo", "🔵", "Clear Blue like the sky."),
                        FlashcardItem("Yellow", "Yellow Color", "y-el-oh", "🟡", "Sunny Yellow like a banana."),
                        FlashcardItem("Green", "Green Color", "g-r-ee-n", "🟢", "Fresh Green like leaf grass.")
                    )
                ),
                CurriculumCategory(
                    id = "toddler_body",
                    name = "Body Parts",
                    items = listOf(
                        FlashcardItem("Eyes", "Seeing Eyes", "eye-z", "👀", "Eyes to see wonderful things."),
                        FlashcardItem("Nose", "Smelling Nose", "n-oh-z", "👃", "Nose to smell flowers."),
                        FlashcardItem("Hands", "Clapping Hands", "h-and-z", "🤲", "Hands to clap and hug."),
                        FlashcardItem("Feet", "Walking Feet", "f-ee-t", "👣", "Feet to walk and jump."),
                        FlashcardItem("Ears", "Listening Ears", "ee-r-z", "👂", "Ears to hear sweet music."),
                        FlashcardItem("Mouth", "Smiling Mouth", "m-ow-th", "👄", "Mouth to smile and eat.")
                    )
                ),
                CurriculumCategory(
                    id = "toddler_family",
                    name = "Family & People",
                    items = listOf(
                        FlashcardItem("Mommy", "Loving Mommy", "m-om-ee", "👩", "Mommy gives warm hugs."),
                        FlashcardItem("Daddy", "Caring Daddy", "d-ad-ee", "👨", "Daddy plays games with me."),
                        FlashcardItem("Baby", "Happy Baby", "b-ay-bee", "👶", "Baby giggles and smiles."),
                        FlashcardItem("Grandma", "Sweet Grandma", "g-ran-mah", "👵", "Grandma tells nice stories."),
                        FlashcardItem("Grandpa", "Kind Grandpa", "g-ran-pah", "👴", "Grandpa takes me on walks."),
                        FlashcardItem("Doctor", "Helping Doctor", "d-ok-ter", "🩺", "Doctor keeps us healthy and strong.")
                    )
                ),
                CurriculumCategory(
                    id = "toddler_actions",
                    name = "Simple Actions & Verbs",
                    items = listOf(
                        FlashcardItem("Eat", "Yummy Food", "ee-t", "🍲", "Eat healthy food every day."),
                        FlashcardItem("Sleep", "Good Night", "s-l-ee-p", "😴", "Sleep tight to grow big and strong."),
                        FlashcardItem("Run", "Fast Running", "r-u-n", "🏃", "Run around and play outside."),
                        FlashcardItem("Jump", "High Jump", "j-u-m-p", "🦘", "Jump up high in the air."),
                        FlashcardItem("Wave", "Friendly Wave", "w-ay-v", "👋", "Wave hello and goodbye."),
                        FlashcardItem("Wash", "Clean Hands", "w-o-sh", "🧼", "Wash hands before meals.")
                    )
                )
            )
        ),
        "phase2" to CurriculumPhase(
            id = "phase2",
            title = "Age 3–4: Preschoolers (Conceptual & Foundational Literacy/Math)",
            categories = listOf(
                CurriculumCategory(
                    id = "preschool_alpha",
                    name = "Alphabet & Phonics",
                    items = listOf(
                        FlashcardItem("A / a", "Apple", "æ - /æ/", "🍎", "A  for Apple, sound /æ/."),
                        FlashcardItem("B / b", "Ball", "b - /b/", "⚽", "B  for Ball, sound /b/."),
                        FlashcardItem("C / c", "Cat", "k - /k/", "🐱", "C  for Cat, sound /k/."),
                        FlashcardItem("D / d", "Dog", "d - /d/", "🐶", "D  for Dog, sound /d/."),
                        FlashcardItem("E / e", "Elephant", "e - /ɛ/", "🐘", "E  for Elephant, sound /ɛ/.")
                    )
                ),
                CurriculumCategory(
                    id = "preschool_math",
                    name = "Early Math & Numbers 1–20",
                    items = listOf(
                        FlashcardItem("1–5", "Numbers One to Five", "1 to 5", "🔢", "Counting 1, 2, 3, 4, 5."),
                        FlashcardItem("6–10", "Numbers Six to Ten", "6 to 10", "🔢", "Counting 6, 7, 8, 9, 10."),
                        FlashcardItem("11–20", "Numbers Eleven to Twenty", "11 to 20", "🔢", "Counting up to twenty!"),
                        FlashcardItem("2D Shapes", "Circle, Square, Triangle, Star", "shapes", "🔷", "Basic 2D shapes: Circle, square, triangle, star, heart, oval."),
                        FlashcardItem("Opposites", "Big / Small, Tall / Short", "opposites", "⚖️", "Size & Opposites: Big/Small, Tall/Short, In/Out, Hot/Cold.")
                    )
                ),
                CurriculumCategory(
                    id = "preschool_world",
                    name = "World Around Us & SEL",
                    items = listOf(
                        FlashcardItem("Vehicles", "Fire truck, Airplane, Train", "transport", "🚒", "Vehicles & Transport: Fire truck, airplane, train, bicycle."),
                        FlashcardItem("Nutrition", "Fruits, Veggies, Snacks", "food", "🥗", "Food & Nutrition: Healthy snacks and fresh fruits."),
                        FlashcardItem("Weather", "Sun, Rain, Snow, Seasons", "weather", "🌦️", "Weather & Seasons: Spring, Summer, Autumn, Winter."),
                        FlashcardItem("Emotions", "Happy, Sad, Angry, Surprised", "sel", "😊", "Social-Emotional Learning: Basic emotions and self-awareness."),
                        FlashcardItem("Manners", "Please, Thank You, Routines", "manners", "🤝", "Manners & Routines: Sharing and brushing teeth.")
                    )
                )
            )
        ),
        "phase3" to CurriculumPhase(
            id = "phase3",
            title = "Age 5–6: Kindergarteners (Early Literacy, Math & Science Exploration)",
            categories = listOf(
                CurriculumCategory(
                    id = "kinder_literacy",
                    name = "Literacy & Phonics",
                    items = listOf(
                        FlashcardItem("Sight Words", "The, And, Was, For", "sight words", "📖", "High-Frequency Sight Words (Dolch / Fry lists)."),
                        FlashcardItem("CVC Words", "Cat, Dog, Pin, Sun", "cvc", "📝", "CVC Words (Consonant-Vowel-Consonant)."),
                        FlashcardItem("Rhyming", "Cat/Hat, Sun/Fun", "rhyme", "🎵", "Rhyming Words and sound patterns."),
                        FlashcardItem("Compound", "Sun + Flower = Sunflower", "compound", "🌻", "Simple Compound Words formation.")
                    )
                ),
                CurriculumCategory(
                    id = "kinder_math",
                    name = "Elementary Math & Time",
                    items = listOf(
                        FlashcardItem("1 to 100", "Counting & 2s, 5s, 10s", "skip count", "💯", "Numbers 1–100 & Counting by 2s, 5s, 10s."),
                        FlashcardItem("Addition", "Visual Equations (🍎 + 🍎)", "math +", "➕", "Basic Addition & Subtraction equations."),
                        FlashcardItem("3D Shapes", "Sphere, Cube, Cylinder, Cone", "3d shapes", "🧊", "Solid 3D geometric shapes."),
                        FlashcardItem("Time & Money", "Clock faces & Coins", "clock", "⏰", "Time & Money Basics: Clock faces (hourly), coins/bills.")
                    )
                ),
                CurriculumCategory(
                    id = "kinder_science",
                    name = "Science, Nature & Social Studies",
                    items = listOf(
                        FlashcardItem("Solar System", "Sun, Moon, Earth, Mars", "space", "🪐", "Solar System & Planets: Sun, Moon, Earth, Mars, Stars."),
                        FlashcardItem("Life Cycles", "Butterfly, Frog, Plant", "lifecycle", "🐛", "Life Cycles: Butterfly, frog, plant growth."),
                        FlashcardItem("Habitats", "Ocean, Jungle, Desert, Forest", "habitat", "🌴", "Habitats and Ecosystems."),
                        FlashcardItem("Community", "Police, Firefighter, Teacher", "helpers", "👩‍🏫", "Community Helpers: Police officer, firefighter, teacher, chef.")
                    )
                )
            )
        ),
        "phase4" to CurriculumPhase(
            id = "phase4",
            title = "Age 7–9: Early Elementary (Subject Mastery & Critical Thinking)",
            categories = listOf(
                CurriculumCategory(
                    id = "elem_grammar",
                    name = "Language Arts & Grammar",
                    items = listOf(
                        FlashcardItem("Parts of Speech", "Nouns, Verbs, Adjectives, Adverbs", "grammar", "📚", "Parts of Speech: Nouns, Verbs, Adjectives, Adverbs."),
                        FlashcardItem("Word Study", "Homophones, Synonyms, Antonyms", "vocab", "🔤", "Homophones & Synonyms/Antonyms (Their/There/They're)."),
                        FlashcardItem("Roots", "Prefix & Suffix (Un-, Re-, -ful)", "morphology", "🌿", "Prefix & Suffix Root Words.")
                    )
                ),
                CurriculumCategory(
                    id = "elem_math",
                    name = "Advanced Math & Measurement",
                    items = listOf(
                        FlashcardItem("Multiplication", "Tables 1 to 12", "times", "✖️", "Multiplication Tables (1 to 12)."),
                        FlashcardItem("Division & Fractions", "1/2, 1/4, 3/4 visual representations", "fractions", "🥧", "Basic Division & Fractions."),
                        FlashcardItem("Measurement", "Length, Weight, Volume units", "units", "📏", "Measurement Units: Length (cm/in), Weight (kg/lb), Volume.")
                    )
                ),
                CurriculumCategory(
                    id = "elem_stem",
                    name = "STEM, Geography & Zoology",
                    items = listOf(
                        FlashcardItem("Human Body", "Skeletal, Digestive, Respiratory", "anatomy", "🦴", "Human Body Systems and Organs."),
                        FlashcardItem("Geography", "World Capitals & Continents", "earth", "🌍", "Geography & Flags: Capitals, continents, landmarks."),
                        FlashcardItem("Matter & Animals", "States of Matter & Classification", "science", "🔬", "States of Matter & Animal Classification (Mammals, reptiles).")
                    )
                )
            )
        )
    )
                        FlashcardItem("A", "Apple", "ah - /æ/", "🍎", "A for Apple! Crisp, red, and juicy."),
                        FlashcardItem("B", "Ball", "buh - /b/", "⚽", "B for Ball! Bouncing round and high."),
                        FlashcardItem("C", "Cat", "kuh - /k/", "🐱", "C for Cat! Soft kitty says Meow."),
                        FlashcardItem("D", "Duck", "duh - /d/", "🦆", "D for Duck! Splashing in the pond quack quack."),
                        FlashcardItem("E", "Elephant", "eh - /e/", "🐘", "E for Elephant! With big gentle ears."),
                        FlashcardItem("F", "Fish", "fuh - /f/", "🐟", "F for Fish! Swimming in the blue stream."),
                        FlashcardItem("G", "Grapes", "guh - /g/", "🍇", "G for Grapes! Sweet, round, purple fruit."),
                        FlashcardItem("H", "House", "huh - /h/", "🏡", "H for House! Cozy home with family."),
                        FlashcardItem("I", "Igloo", "ih - /ɪ/", "🧊", "I for Igloo! A cool dome built from snow."),
                        FlashcardItem("J", "Juice", "juh - /dʒ/", "🧃", "J for Juice! Sweet and refreshing orange juice."),
                        FlashcardItem("K", "Kite", "kuh - /k/", "🪁", "K for Kite! Flying high in the windy sky."),
                        FlashcardItem("L", "Lion", "luh - /l/", "🦁", "L for Lion! The brave king of the jungle."),
                        FlashcardItem("M", "Moon", "muh - /m/", "🌙", "M for Moon! Glowing brightly in the night sky."),
                        FlashcardItem("N", "Nest", "nuh - /n/", "🪺", "N for Nest! A cozy home for baby birds."),
                        FlashcardItem("O", "Orange", "aw - /ɒ/", "🍊", "O for Orange! Round, bright, and citrus sweet."),
                        FlashcardItem("P", "Penguin", "puh - /p/", "🐧", "P for Penguin! Waddling happily on the ice."),
                        FlashcardItem("Q", "Queen", "kwuh - /kw/", "👑", "Q for Queen! Wearing a sparkling royal crown."),
                        FlashcardItem("R", "Rainbow", "ruh - /r/", "🌈", "R for Rainbow! Seven vibrant colors after the rain."),
                        FlashcardItem("S", "Sun", "suh - /s/", "☀️", "S for Sun! Warm sunshine welcoming the day."),
                        FlashcardItem("T", "Tree", "tuh - /t/", "🌳", "T for Tree! Tall green branches giving cool shade."),
                        FlashcardItem("U", "Umbrella", "uh - /ʌ/", "☂️", "U for Umbrella! Keeping us dry in rainy showers."),
                        FlashcardItem("V", "Violin", "vuh - /v/", "🎻", "V for Violin! Playing sweet gentle melodies."),
                        FlashcardItem("W", "Whale", "wuh - /w/", "🐋", "W for Whale! Gliding through the deep blue ocean."),
                        FlashcardItem("X", "Xylophone", "zyl - /z/", "🎼", "X for Xylophone! Rainbow chime bars making music."),
                        FlashcardItem("Y", "Yo-yo", "yuh - /j/", "🪀", "Y for Yo-yo! Spinning smoothly up and down."),
                        FlashcardItem("Z", "Zebra", "zuh - /z/", "🦓", "Z for Zebra! Proud stripes galloping across the plains.")
                    )
                ),
                CurriculumCategory(
                    id = "tamil",
                    name = "தமிழ் உயிர் எழுத்துக்கள் (Tamil)",
                    items = listOf(
                        FlashcardItem("அ", "அம்மா (Amma)", "Sound: 'Ah'", "🤱", "அம்மா! அன்பான அம்மா.", true),
                        FlashcardItem("ஆ", "ஆடு (Aadu)", "Sound: 'Aah'", "🐐", "ஆடு! துள்ளி ஓடும் வெள்ளாடு.", true),
                        FlashcardItem("இ", "இலை (Ilai)", "Sound: 'Ee'", "🍃", "இலை! மரத்தில் அசையும் பச்சை இலை.", true),
                        FlashcardItem("ஈ", "ஈட்டி (Eetti)", "Sound: 'Eee'", "🗡️", "ஈட்டி! கூர்மையான ஈட்டி.", true),
                        FlashcardItem("உ", "உரல் (Ural)", "Sound: 'Oo'", "🥣", "உரல்! மாவு இடிக்கும் மர உரல்.", true),
                        FlashcardItem("ஊ", "ஊஞ்சல் (Oonjal)", "Sound: 'Ooo'", "🎪", "ஊஞ்சல்! காற்றில் ஆடும் வண்ண ஊஞ்சல்.", true),
                        FlashcardItem("எ", "எறும்பு (Erumbu)", "Sound: 'Eh'", "🐜", "எறும்பு! சுறுசுறுப்பான குட்டி எறும்பு.", true),
                        FlashcardItem("ஏ", "ஏணி (Eani)", "Sound: 'Aay'", "🪜", "ஏணி! மேலே ஏற உதவும் ஏணி.", true),
                        FlashcardItem("ஐ", "ஐந்து (Ainthu)", "Sound: 'Eye'", "🖐️", "ஐந்து! கையில் இருக்கும் ஐந்து விரல்கள்.", true),
                        FlashcardItem("ஒ", "ஒட்டகம் (Ottagam)", "Sound: 'Oh'", "🐫", "ஒட்டகம்! பாலைவனத்து நண்பன் ஒட்டகம்.", true),
                        FlashcardItem("ஓ", "ஓடம் (Odam)", "Sound: 'Oah'", "⛵", "ஓடம்! ஆற்றில் மிதக்கும் சின்ன ஓடம்.", true),
                        FlashcardItem("ஔ", "ஔவையார் (Avvaiyar)", "Sound: 'Ow'", "👵", "ஔவையார்! நற்பண்புகளைக் கற்பித்த பாட்டி.", true),
                        FlashcardItem("ஃ", "எஃகு (Ehgu)", "Sound: 'Akh'", "🛡️", "ஆய்த எழுத்து! மூன்று புள்ளிகள் கொண்ட எஃகு வாள்.", true)
                    )
                ),
                CurriculumCategory(
                    id = "numerals",
                    name = "Numerals (1-10)",
                    items = listOf(
                        FlashcardItem("1", "One", "wun", "1️⃣", "Number 1! The number one."),
                        FlashcardItem("2", "Two", "too", "2️⃣", "Number 2! The number two."),
                        FlashcardItem("3", "Three", "three", "3️⃣", "Number 3! The number three."),
                        FlashcardItem("4", "Four", "for", "4️⃣", "Number 4! The number four."),
                        FlashcardItem("5", "Five", "fyve", "5️⃣", "Number 5! The number five."),
                        FlashcardItem("6", "Six", "siks", "6️⃣", "Number 6! The number six."),
                        FlashcardItem("7", "Seven", "sev-en", "7️⃣", "Number 7! The number seven."),
                        FlashcardItem("8", "Eight", "ayt", "8️⃣", "Number 8! The number eight."),
                        FlashcardItem("9", "Nine", "nyne", "9️⃣", "Number 9! The number nine."),
                        FlashcardItem("10", "Ten", "ten", "🔟", "Number 10! The number ten.")
                    )
                ),
                CurriculumCategory(
                    id = "shapes",
                    name = "Shapes & Colors",
                    items = listOf(
                        FlashcardItem("Circle", "Red Circle", "sur-kl", "🔴", "A round red circle like a bouncy ball!"),
                        FlashcardItem("Square", "Blue Square", "skwair", "🟦", "A blue square with four straight sides."),
                        FlashcardItem("Triangle", "Yellow Triangle", "try-ang-gl", "🔺", "A yellow triangle like a slice of pizza!"),
                        FlashcardItem("Star", "Golden Star", "stahr", "⭐", "A twinkling golden star shining in the night."),
                        FlashcardItem("Heart", "Pink Heart", "hahrt", "💖", "A pink heart full of love and warm hugs."),
                        FlashcardItem("Diamond", "Green Diamond", "dye-mund", "💎", "A sparkling green diamond like a flying kite!")
                    )
                ),
                CurriculumCategory(
                    id = "hindi",
                    name = "हिंदी वर्णमाला (Hindi Swar)",
                    items = listOf(
                        FlashcardItem("अ", "अनार (Anar)", "ah - /a/", "🍎", "अ से अनार! मीठे और लाल दाने।", true),
                        FlashcardItem("आ", "आम (Aam)", "aah - /aa/", "🥭", "आ से आम! फलों का राजा मीठा आम।", true),
                        FlashcardItem("इ", "इमली (Imli)", "ee - /i/", "🫒", "इ से इमली! खट्टी-मीठी इमली।", true),
                        FlashcardItem("ई", "ईख (Eekh)", "eeh - /ee/", "🎋", "ई से ईख! मीठा गन्ने का रस।", true),
                        FlashcardItem("उ", "उल्लू (Ullu)", "oo - /u/", "🦉", "उ से उल्लू! रात को जागने वाला पक्षी।", true),
                        FlashcardItem("ऊ", "ऊन (Oon)", "oo - /oo/", "🧶", "ऊ से ऊन! जिससे बनते हैं गरम स्वेटर।", true),
                        FlashcardItem("ऋ", "ऋषि (Rishi)", "ri - /ri/", "🧘‍♂️", "ऋ से ऋषि! ज्ञान देने वाले महर्षि।", true),
                        FlashcardItem("ए", "एड़ी (Aidi)", "ay - /e/", "🦶", "ए से एड़ी! पैरों की एड़ी।", true),
                        FlashcardItem("ऐ", "ऐनक (Ainak)", "ai - /ai/", "👓", "ऐ से ऐनक! आँखों पर पहनने का चश्मा।", true),
                        FlashcardItem("ओ", "ओखली (Okhli)", "oh - /o/", "🥣", "ओ से ओखली! मसाले कूटने का बर्तन।", true),
                        FlashcardItem("औ", "औरत (Aurat)", "ow - /au/", "👩", "औ से औरत! ममता की मूर्ति।", true)
                    )
                ),
                CurriculumCategory(
                    id = "body_parts",
                    name = "Body Parts (शरीर के अंग)",
                    items = listOf(
                        FlashcardItem("Eyes", "आँखें (Eyes)", "eyez", "👀", "Eyes to see the beautiful colorful world."),
                        FlashcardItem("Ears", "कान (Ears)", "eerz", "👂", "Ears to listen to cheerful music and stories."),
                        FlashcardItem("Nose", "नाक (Nose)", "nohz", "👃", "Nose to smell sweet flowers and warm food."),
                        FlashcardItem("Mouth", "मुँह (Mouth)", "mowth", "👄", "Mouth to speak kindly and smile happily."),
                        FlashcardItem("Hands", "हाथ (Hands)", "handz", "👐", "Hands to clap, build blocks, and share hugs."),
                        FlashcardItem("Feet", "पैर (Feet)", "feet", "🦶", "Feet to walk, run, and jump around."),
                        FlashcardItem("Head", "सिर (Head)", "hed", "👶", "Head with soft hair and smart thoughts."),
                        FlashcardItem("Teeth", "दाँत (Teeth)", "teeth", "🦷", "Teeth shining clean and bright white!")
                    )
                ),
                CurriculumCategory(
                    id = "animals",
                    name = "Animal Names (जानवरों के नाम)",
                    items = listOf(
                        FlashcardItem("Lion", "शेर (Lion)", "lyun", "🦁", "Lion is the brave king of the jungle."),
                        FlashcardItem("Elephant", "हाथी (Elephant)", "el-uh-fuhnt", "🐘", "Elephant is a gentle giant with a long trunk."),
                        FlashcardItem("Tiger", "बाघ (Tiger)", "ty-ger", "🐅", "Tiger has gorgeous orange and black stripes."),
                        FlashcardItem("Monkey", "बंदर (Monkey)", "mung-kee", "🐒", "Monkey loves swinging playfully from tree branches."),
                        FlashcardItem("Zebra", "जेब्रा (Zebra)", "zee-bruh", "🦓", "Zebra has stylish black and white stripes."),
                        FlashcardItem("Giraffe", "जिराफ (Giraffe)", "ji-raf", "🦒", "Giraffe has a long neck reaching tall trees."),
                        FlashcardItem("Bear", "भालू (Bear)", "bair", "🐻", "Bear has soft fluffy fur and loves honey."),
                        FlashcardItem("Rabbit", "खरगोश (Rabbit)", "rab-it", "🐰", "Rabbit jumps hop hop across the green grass."),
                        FlashcardItem("Fox", "लोमड़ी (Fox)", "foks", "🦊", "Fox is a smart and clever forest animal."),
                        FlashcardItem("Panda", "पांडा (Panda)", "pan-duh", "🐼", "Panda loves munching green bamboo shoots.")
                    )
                ),
                CurriculumCategory(
                    id = "vegetables",
                    name = "Vegetable Names (सब्जियों के नाम)",
                    items = listOf(
                        FlashcardItem("Carrot", "गाजर (Carrot)", "kar-ut", "🥕", "Carrot is a crunchy orange vitamin veggie."),
                        FlashcardItem("Tomato", "टमाटर (Tomato)", "tuh-mah-toh", "🍅", "Tomato is a juicy red round vegetable."),
                        FlashcardItem("Potato", "आलू (Potato)", "puh-tay-toh", "🥔", "Potato is a yummy healthy root vegetable."),
                        FlashcardItem("Broccoli", "ब्रोकोली (Broccoli)", "brok-uh-lee", "🥦", "Broccoli looks like little green trees."),
                        FlashcardItem("Cucumber", "खीरा (Cucumber)", "kyoo-kum-ber", "🥒", "Cucumber is cool, crisp, and fresh."),
                        FlashcardItem("Pumpkin", "कद्दू (Pumpkin)", "pump-kin", "🎃", "Pumpkin is a big golden yellow vegetable."),
                        FlashcardItem("Spinach", "पालक (Spinach)", "spin-ich", "🥬", "Spinach gives us strong and healthy muscles."),
                        FlashcardItem("Peas", "मटर (Peas)", "peez", "🟢", "Peas are sweet little green pods.")
                    )
                ),
                CurriculumCategory(
                    id = "fruits",
                    name = "Fruit Names (फलों के नाम)",
                    items = listOf(
                        FlashcardItem("Apple", "सेब (Apple)", "ap-ul", "🍎", "Apple is crisp, sweet, and red."),
                        FlashcardItem("Banana", "केला (Banana)", "buh-nan-uh", "🍌", "Banana gives sweet energy for playing."),
                        FlashcardItem("Mango", "आम (Mango)", "mang-goh", "🥭", "Mango is the juicy king of sweet fruits."),
                        FlashcardItem("Grapes", "अंगूर (Grapes)", "grayps", "🍇", "Grapes grow in sweet purple and green clusters."),
                        FlashcardItem("Orange", "संतरा (Orange)", "or-inj", "🍊", "Orange is packed with fresh vitamin C."),
                        FlashcardItem("Strawberry", "स्ट्रॉबेरी (Strawberry)", "straw-ber-ee", "🍓", "Strawberry is a sweet little red berry."),
                        FlashcardItem("Watermelon", "तरबूज (Watermelon)", "waw-ter-mel-un", "🍉", "Watermelon is big, cool, and refreshing."),
                        FlashcardItem("Pineapple", "अनानास (Pineapple)", "pyne-ap-ul", "🍍", "Pineapple is a delicious tropical crown fruit.")
                    )
                )
            )
        ),
        "phase2" to CurriculumPhase(
            id = "phase2",
            title = "Phase 2: Word Builders (Ages 4-5)",
            categories = listOf(
                CurriculumCategory(
                    id = "words",
                    name = "CVC Word Families",
                    items = listOf(
                        FlashcardItem("CAT", "C - A - T", "/kæt/", "🐱", "C-A-T spells Cat! A furry kitten."),
                        FlashcardItem("SUN", "S - U - N", "/sʌn/", "☀️", "S-U-N spells Sun! It brings warm morning light."),
                        FlashcardItem("DOG", "D - O - G", "/dɒɡ/", "🐶", "D-O-G spells Dog! A playful puppy friend."),
                        FlashcardItem("BUS", "B - U - S", "/bʌs/", "🚌", "B-U-S spells Bus! Riding with friends to school."),
                        FlashcardItem("PIG", "P - I - G", "/pɪɡ/", "🐷", "P-I-G spells Pig! Cheerful piggy rolling in mud."),
                        FlashcardItem("HEN", "H - E - N", "/hen/", "🐔", "H-E-N spells Hen! Clucking gently on the farm.")
                    )
                ),
                CurriculumCategory(
                    id = "helpers",
                    name = "Community Helpers",
                    items = listOf(
                        FlashcardItem("Firefighter", "Safety", "fyer-fye-ter", "🧑‍🚒", "Firefighters bravely keep our neighborhoods safe."),
                        FlashcardItem("Doctor", "Health", "dok-ter", "👩‍⚕️", "Doctors care for us and help us stay healthy."),
                        FlashcardItem("Teacher", "Education", "tee-cher", "🧑‍🏫", "Teachers guide us to read, count, and discover."),
                        FlashcardItem("Police", "Protection", "puh-lees", "👮", "Police officers protect our streets and help others.")
                    )
                )
            )
        ),
        "phase3" to CurriculumPhase(
            id = "phase3",
            title = "Phase 3: Discoverers (Ages 6-7)",
            categories = listOf(
                CurriculumCategory(
                    id = "digraphs",
                    name = "Phonics Digraphs (CH/SH)",
                    items = listOf(
                        FlashcardItem("Ship", "SH", "/ʃɪp/", "🚢", "S-H makes the quiet Shhh sound, like a giant ocean ship."),
                        FlashcardItem("Chair", "CH", "/tʃeər/", "🪑", "C-H makes the cheerful Ch-Ch sound, like a comfy chair."),
                        FlashcardItem("Thumb", "TH", "/θʌm/", "👍", "T-H makes the soft Th sound, giving a thumbs up!"),
                        FlashcardItem("Whale", "WH", "/weɪl/", "🐋", "W-H makes the whistling Wh sound, like a friendly whale.")
                    )
                ),
                CurriculumCategory(
                    id = "space",
                    name = "Solar System & Space",
                    items = listOf(
                        FlashcardItem("Sun", "Star", "sun", "☀️", "The Sun is a giant star giving warmth and light."),
                        FlashcardItem("Earth", "Home", "erth", "🌍", "Planet Earth, our wonderful home with oceans and forests."),
                        FlashcardItem("Moon", "Satellite", "moon", "🌙", "The Moon orbits Earth and glows softly at night."),
                        FlashcardItem("Mars", "Red Planet", "mahrz", "🪐", "Mars is known as the red rocky planet next to Earth.")
                    )
                )
            )
        )
    )

    val stories = listOf(
        Story(
            id = "story1",
            title = "Pip the Brave Penguin",
            theme = "Courage & Discovery",
            coverEmoji = "🐧",
            pages = listOf(
                StoryPage("Pip was a happy penguin who loved to slide on ice.", listOf("Pip", "was", "a", "happy", "penguin", "who", "loved", "to", "slide", "on", "ice."), "❄️🐧🧊"),
                StoryPage("Today Pip was nervous about taking his very first dive.", listOf("Today", "Pip", "was", "nervous", "about", "taking", "his", "very", "first", "dive."), "🌊👀🐧"),
                StoryPage("Mom held his flipper and said: Take a deep breath!", listOf("Mom", "held", "his", "flipper", "and", "said:", "Take", "a", "deep", "breath!"), "❤️🐧🤗"),
                StoryPage("SPLASH! Pip swam fast and found sparkly fish friends!", listOf("SPLASH!", "Pip", "swam", "fast", "and", "found", "sparkly", "fish", "friends!"), "🐬💦🎉")
            ),
            moral = "Courage brings exciting discoveries!"
        ),
        Story(
            id = "story2",
            title = "Sammy the Seed",
            theme = "Patience & Growth",
            coverEmoji = "🌱",
            pages = listOf(
                StoryPage("Sammy was a tiny seed tucked cozy in the warm soil.", listOf("Sammy", "was", "a", "tiny", "seed", "tucked", "cozy", "in", "the", "warm", "soil."), "🟫🌰💤"),
                StoryPage("Gentle rain gave sweet water every morning.", listOf("Gentle", "rain", "gave", "sweet", "water", "every", "morning."), "🌧️💧🌱"),
                StoryPage("Sammy reached upward to the bright shining sun.", listOf("Sammy", "reached", "upward", "to", "the", "bright", "shining", "sun."), "☀️🌿✨"),
                StoryPage("Now Sammy is a golden sunflower dancing with the bees!", listOf("Now", "Sammy", "is", "a", "golden", "sunflower", "dancing", "with", "the", "bees!"), "🌻🐝🍯")
            ),
            moral = "With gentle patience, you grow big and strong!"
        )
    )

    val quizQuestions = listOf(
        QuizQuestion("Which animal says 'Moo'?", listOf(QuizOption("🐮", "Cow", true), QuizOption("🐱", "Cat", false), QuizOption("🐶", "Dog", false))),
        QuizQuestion("Find the Round Circle:", listOf(QuizOption("🔴", "Circle", true), QuizOption("🔺", "Triangle", false), QuizOption("🟦", "Square", false))),
        QuizQuestion("Which fruit is sweet and red?", listOf(QuizOption("🍎", "Apple", true), QuizOption("🍋", "Lemon", false), QuizOption("🥥", "Coconut", false))),
        QuizQuestion("Who helps put out fires?", listOf(QuizOption("🧑‍🚒", "Firefighter", true), QuizOption("🧑‍🍳", "Chef", false), QuizOption("🎨", "Painter", false))),
        QuizQuestion("What shines in the sky at night?", listOf(QuizOption("🌙", "Moon", true), QuizOption("☀️", "Sun", false), QuizOption("🪁", "Kite", false))),
        QuizQuestion("Which animal is the king of the jungle?", listOf(QuizOption("🦁", "Lion", true), QuizOption("🐧", "Penguin", false), QuizOption("🐬", "Dolphin", false)))
    )

    val habits = mutableListOf(
        Habit("h1", "Brush My Teeth", "Morning & Night", "🪥", true, "Brush circles gently for 2 minutes!"),
        Habit("h2", "Drink Fresh Water", "All Day", "💧", false, "Keeps your brain fast and bright."),
        Habit("h3", "Put Away My Toys", "After Play", "🧸", true, "Keeps your room clean and cozy."),
        Habit("h4", "Wash Hands with Soap", "Before Eating", "🧼", false, "Sing your ABCs while lathering!")
    )

    val melodySongs = listOf(
        MelodySong("twinkle", "Twinkle Little Star", "⭐", listOf(
            MelodyNote("C", 261.63, 450), MelodyNote("C", 261.63, 450),
            MelodyNote("G", 392.00, 450), MelodyNote("G", 392.00, 450),
            MelodyNote("A", 440.00, 450), MelodyNote("A", 440.00, 450),
            MelodyNote("G", 392.00, 850)
        )),
        MelodySong("mary", "Mary Had a Little Lamb", "🐑", listOf(
            MelodyNote("E", 329.63, 450), MelodyNote("D", 293.66, 450),
            MelodyNote("C", 261.63, 450), MelodyNote("D", 293.66, 450),
            MelodyNote("E", 329.63, 450), MelodyNote("E", 329.63, 450),
            MelodyNote("E", 329.63, 850)
        )),
        MelodySong("macdonald", "Old MacDonald Had a Farm", "🚜", listOf(
            MelodyNote("G", 392.00, 400), MelodyNote("G", 392.00, 400),
            MelodyNote("G", 392.00, 400), MelodyNote("D", 293.66, 400),
            MelodyNote("E", 329.63, 400), MelodyNote("E", 329.63, 400),
            MelodyNote("D", 293.66, 750)
        )),
        MelodySong("rowboat", "Row, Row, Row Your Boat", "⛵", listOf(
            MelodyNote("C", 261.63, 550), MelodyNote("C", 261.63, 550),
            MelodyNote("C", 261.63, 400), MelodyNote("D", 293.66, 250),
            MelodyNote("E", 329.63, 600)
        ))
    )
}
