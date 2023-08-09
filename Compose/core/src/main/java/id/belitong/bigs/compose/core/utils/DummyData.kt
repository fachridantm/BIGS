package id.belitong.bigs.compose.core.utils

import id.belitong.bigs.compose.core.domain.model.Biodiversity
import id.belitong.bigs.compose.core.domain.model.Geosite
import id.belitong.bigs.compose.core.domain.model.Order
import id.belitong.bigs.compose.core.domain.model.Plant

object DummyData {
    fun getAllGeosites() = listOf(
        Geosite(
            id = 1,
            name = "Moss Hill",
            summary = "The hill itself is the highest point in the southeast region of Belitung Island",
            type = "Hill",
            desc = "The hill itself is the highest point in the southeast region of Belitung Island. Visitors can enjoy a beautiful scenery at the peak of Moss Hill. \n" +
                    "\n" +
                    "The Moss Hill Geosite is a conservation area with rich biological diversity atop the flysch metasediment rock in the Kelapa Kampit Formation. The biome consists of numerous types of tropical rainforest moss and orchids including around 9 species of moss (Bryophytasp.), around 9 species of orchids (Orchidaceae), and pasakbumi (Eurycomalongifolia).",
            plant = "Tropical Pitcher (Nepenthes)",
            animal = "-",
            distance = 500,
            location = "Limbongan, Kec. Gantung, East Belitung Regency",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560494357865-JI4N7MAOI1B2IVO99HLC/Martin-Gunung-Lumut-01.jpg?format=1500w",
        ),
        Geosite(
            id = 2,
            name = "Juru Seberang",
            summary = "Juru Seberang Geosite: a tin-rich alluvial area formed by weathering of granite and metasediments",
            type = "Former Mining Area",
            desc = "The Juru Seberang geosite is a tin rich-alluvial area that resulted from weathering of Tanjung Pandang granite/ S-type granite mixed with weathering from the metasediment of Kelapakampit Formation. A lamp shell (Lingula Unguis) was found in the area, the animal representing evolution from the Cambrian Period (542 million years ago). \n" +
                    "\n" +
                    "This geosite offers white sandy coasts with sea pines (Casuarina equisetifolia), a tracking path along the mangrove forest and traditional fishing attractions.",
            plant = "-",
            animal = "-",
            distance = 1000,
            location = "Juru Seberang, Kec. Tj. Pandan, Belitung Regency",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558849946386-EYT83UVSON1V10TCFIEO/Juru-Seberang-7.jpg?format=500w",
        ),
        Geosite(
            id = 3,
            name = "Terong Tourism Village",
            summary = "Terong tourism village: 4 sub-sites, ex-mining land management, community-based tourism",
            type = "Environmental Conservation",
            desc = "Terong tourism village is an environmental conservation geosite consisting of four sub-sites, namely the agro-tourism focused Bebatu, the nature-themed Bukit Tebalu, the creative tourism-based Aik Rusa Berehun and the surrounding mangrove forest. The geosite is valued as an example of proper ex-mining land management and a good mining activity prevention strategy using community-based tourism activity.",
            plant = "-",
            animal = "-",
            distance = 800,
            location = "Aik Rusa Berehun, Terong, Sijuk, Kabupaten Belitung",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558768149463-9FO40V8K4XJ3U1Y1CQHC/BG-Terong-1.jpg?format=1000w",
        ),
        Geosite(
            id = 4,
            name = "Peramun Hill Granite Forest",
            summary = "The name ‘Peramun’, meaning concoction, comes from the abundance of medicinal herbs in the area",
            type = "Hill",
            desc = "This site is a forest consisting of 149 species of plants and covering 350. The name ‘Peramun’, meaning concoction, comes from the abundance of medicinal herbs in the area; many being said to be effective in recovery from malaria, childbirth, diabetes,high blood pressure and other such diseases. \n" +
                    "\n" +
                    "These herbs are used in the production of a unique souvenir called geo-herbals. Other plants are also available as souvenirs in the form of craft products. The rare Horsfield’s Tarsier (Chepalopacus bancanussaltator) can also be found here. Peramun Hill also has an excellent TOR landscape typical of Belitung Island and its surroundings.",
            plant = "-",
            animal = "Belitong Tersier (Tarsius Bancanus Saltator)",
            distance = 1500,
            location = "Jl. Peramun, Air Selumar, Sijuk, Kabupaten Belitung",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558975216815-9IOOH4H0AO2KKZ4PNGWQ/peramun4.jpg?format=1000w",
        ),
        Geosite(
            id = 5,
            name = "Tanjung Kelayang",
            summary = "This geosite offers an exotic view from the coastal rock structures composed of Triassic",
            type = "Granite Rock",
            desc = "The Tanjung Kelayang Granite Geosite is located at the end of the Southeast Asian granite province. \n" +
                    "\n" +
                    "This geosite offers an exotic view from the coastal rock structures composed of Triassic (213 million years ago) granite Tor morphology undersea.  The granite Tor sea environment morphology has been recognized as a world class geological heritage. \n" +
                    "\n" +
                    "A small island cluster is formed from these rock structures atop very clear sea water, forming a beautiful panorama. Visitors can enjoy ‘island hopping’ between the Garuda island, Kelayang Island, Pasir island, Kepayang island, BatuBer- layar island, Burung Island, Malang Ara island, and Lengkuas island. The biodiversity at the sea floor is also a popular attraction. Other culturally significant attractions include a colonial lighthouse at Lengkuas Island.",
            plant = "-",
            animal = "-",
            distance = 12000,
            location = "Plasa Tanjung Kelayang, Keciput, Sijuk, Belitung Regency",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560870912169-JGL7VX8VODXUV2FHMTIJ/DSC_8889-copy-small.jpg?format=1500w",
        ),
        Geosite(
            id = 6,
            name = "Batu Bedil",
            summary = "The Batu Bedil is named after a legend where a loud rifle-like sound blasted from the rock",
            type = "Granite Rock",
            desc = "The Batu Bedil (Bedil means rifle) is named after a legend where a loud rifle-like sound blasted from the rock. Its cause was a large wave that hit a small hole on the rock connected to a cavity within the Triassic granite, causing a loud sound that could be heard from 30 km away.\n" +
                    "\n" +
                    "The Bedil rock offers a stunning beach panorama from the Triassic Granite rock to the beach view facing Natuna Sea. The area boasts a beautiful TOR landscape and a vast stretch of granite along the coast. Its positioning had als made it a suitable passageway for visitors from the north to enter through the Padang River.",
            plant = "-",
            animal = "-",
            distance = 13000,
            location = "Sungai Padang, Sijuk, Kabupaten Belitung",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560478707636-9IGQ54HREHO80XBYBKLC/Martin-Batu-Bedil-08.jpg?format=1500w",
        ),
        Geosite(
            id = 7,
            name = "Nam Salu Open Pit",
            summary = "According to locals, 9 levels of underground mining sites were created during its active period",
            type = "Former Mining Area",
            desc = "Tin mining activities in Kelapa Kampit and Kik Karak Mountain are called ‘open holes’ because this method of mining involves opening up massive, volcano crater-like holes, after which further horizontal and vertical tunnels are dug in. According to locals, 9 levels of underground mining sites were created during its active period. \n" +
                    "\n" +
                    "In addition to the former mine, the geosite is also culturally significant; it is located near an ancestral site of the Kampit community, Kik Karak, which is revered by both the local and ethnic Chinese population.",
            plant = "-",
            animal = "Tarsius (Chepalopachusbancanus Saltator)",
            distance = 15000,
            location = "Kampong Gunong, Senyubuk, Kec. Klp. Kampit, Kabupaten Belitung Timur",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558970893738-X3BO2VX1PBE34AHDRVV7/nam-salu-7.jpg?format=1000w",
        ),
        Geosite(
            id = 8,
            name = "Batu Pulas",
            summary = "Batu Pulas is a represents the Kelapa Kampit metasediment to quartzite & magnetic rocks",
            type = "Granite Rock",
            desc = "The Batu Pulas is a representation of the Kelapa Kampit metasediment that became quartzite rocks and magnetic rocks as a result of basaltic lava intrusion.\n" +
                    "\n" +
                    "Visitors can enjoy the hilly landscapes, valleys and plain forests of the nearby Kerangas region alongside beaches and beautiful small islands. The area also has a wealth of flora & fauna in the within the Kerangas and other forests on the small islands.",
            plant = "-",
            animal = "-",
            distance = 14000,
            location = "Dusun Mengguru, Cendil, Kec. Klp. Kampit, Kabupaten Belitung Timur",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560485122888-WEC7W4U7BRBTOKXBR5BC/Martin-Batu-Pulas-07.jpg?format=1500w",
        ),
        Geosite(
            id = 9,
            name = "Kerangas Forest",
            summary = "Kerangas forests are characterized by extreme land conditions and are very sensitive to disturbances like fire",
            type = "Forest",
            desc = "Kerangas forests are characterized by their extreme land conditions and are very sensitive to disturbances such as fire. The Cendil forest is one of such. This area contains a biome of unique organisms, including the ‘sapu-sapu’ (Baeckeafrutescens L), mushrooms and drosera (Droseraburmanii); the fauna here are also known for their medicinal benefits. Such organisms are made into ‘geo-herbal’ products used in natural treatments of diseases and foodstuffs by the local community.",
            plant = "Sapu-Sapu (Baeckeafrutescens L.), Jamur, & Drosera (Droseraburmanii)",
            animal = "-",
            distance = 1200,
            location = "Desa Cendil Kec. Kelapa Kampit - Kab. Belitung Timur",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560527297986-QMM6V02E3OXL849602QX/Martin-Kerangas-Cendil-01.jpg?format=1500w",
        ),
        Geosite(
            id = 10,
            name = "Tebat Rasau Cenozoic",
            summary = "This ancient swamp is an alluvial swamp characterized by an abundance of Pandanus helicopus",
            type = "Swamp",
            desc = "This ancient swamp is an alluvial swamp characterized by an abundance of Pandanus helicopus, a flora that develops well in tidal conditions free from the brackish water.  \n" +
                    "\n" +
                    "Tebat Rasau is particularly a home for ornamental arowana fish (Seleropages formosus), ampong fish (Channa marulius), and green spotted puffer fish (Tetraodon nigroviridis).  \n" +
                    "\n" +
                    "The discovery of silver Arowana (Osteoglossumbicir- rhodium) and Ampong fish (Channamarulius) that cannot be found in the western part of Belitung suggests a biogeographic phenomenon that took place since the Ice Age when Belitung and the sea around it still landed, part of Sundaland.",
            plant = "Rasau (Pandanus Helicopus)",
            animal = "Arwana (Seleropages Formosus), Ikan Ampong (Channa Marulius), Ikan Buntal Hijau (Tetraodon Nigroviridis), & Arwana Perak (Osteoglossumbicir-rhosum)",
            distance = 1800,
            location = "Desa Lintang, Kecamatan Simpang Renggiang, Kabupaten Belitung Timur",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558768708896-ZMMK94GMX9BQM36NURC7/Tebat-Rasau-2.jpg?format=1000w",
        ),
        Geosite(
            id = 11,
            name = "Burung Mandi Cretaceous Granidiorite",
            summary = "The Burung Mandi Chalk Granodiorite is a large granite rock formation that sits above the Burung Mandi coast",
            type = "Granite Rock",
            desc = "Granodiorit Kapur Burung Mandi adalah formasi batuan granit besar yang berada di atas pantai Burung Mandi. Batuan ini mirip dengan granit, namun mengandung lebih banyak feldspar plagioklas daripada feldspar ortoklas, karakteristik umum granit. Bukit ini juga merupakan tempat berdirinya vihara tertua dan terbesar di Belitung: Vihara Dewi Kuan Im, sebuah ikon penting keberagaman agama di Belitung.",
            plant = "Pohon Pinus (Pinus)",
            animal = "-",
            distance = 2000,
            location = "Desa Burung Mandi, Kecamatan Damar, Kabupaten Belitung Timur",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560525851098-4BLOLV25BDIS33RIP6RM/Martin-Burong-Mandi-02.jpg?format=1500w",
        ),
        Geosite(
            id = 12,
            name = "Siantu Pillow Lava",
            summary = "The rock is estimated to have been formed around 60 million years ago",
            type = "Granite Rock",
            desc = "These pillow lava structures are composed of volcanic plagioclase, pyroxene, chlorite and calcite. \n" +
                    "\n" +
                    "The rock is estimated to have been formed during the Late Cretaceous period (around 60 million years ago). The site is valuable as a representation of the magmatic intrusion resulting in basaltic rock and breccinini forming an environment of plutonic granite and metasedimentary rock.",
            plant = "-",
            animal = "-",
            distance = 20000,
            location = "Sijuk, Belitung Regency",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1559196919933-IHM80C8B2TVVY1KE36YS/BG-Siantu.jpg?format=1000w",
        ),
        Geosite(
            id = 13,
            name = "Tajam Mountain",
            summary = "This area boasts a high biodiversity in the large amounts of flora and fauna",
            type = "Mountain",
            desc = "Tajam (meaning sharp) Mountain is a formation comprised of sediments of siltstone, tin and quartz. This unit is suggested to be involved with the Kelapa Kampit formation in the Permo-Carboniferous era.  \n" +
                    "\n" +
                    "Tajam Mountain boasts the highest point in Belitong Island and is located on the meeting point between two mountain ranges going northwest-southeast and northeast-southwest.   \n" +
                    "\n" +
                    "The Gurok Beraye waterfall is also located within the hiking trail. This area boasts a high biodiversity in the large amounts of flora and fauna within the Kerangas Jungle and a high cultural significance in the royal Badau tombs within.",
            plant = "-",
            animal = "-",
            distance = 17000,
            location = "Kacang Butor, Badau, Kabupaten Belitung",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1559197312436-H8ZMGZ1SHYBKKKBC1K34/BG-Tajam-Mountain.jpg?format=1000w",
        ),
        Geosite(
            id = 14,
            name = "Baginda Rocks",
            summary = "The two rocks are according to local folklore called the Meninda Men and Bukit Baginda Wanita hills.",
            type = "Granite Rock",
            desc = "Batuan itu sendiri terbentuk dari kuarsa, feldspar, plagioklas, biotit, hornblende dan material sekunder berupa aktor, karbonat, limonit dan oksida besi. Geosite ini tidak mengandung timah sehingga terhindar dari aktivitas penambangan.  \n" +
                    "\n" +
                    "Geosite ini penting karena mewakili granit tipe-I dalam bentuk adamelit, sebuah granit yang dibedakan dari granodiorit dalam hal kandungan plagioklas.",
            plant = "-",
            animal = "-",
            distance = 10000,
            location = "Desa Padang Kandis, Membalong, Kabupaten Belitung",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558974816297-JNGRNG4UXWQN1MVE5OKI/batu-baginde.jpg?format=1000w",
        ),
        Geosite(
            id = 15,
            name = "Punai Beach",
            summary = "This geosite is important as representative of I-type granite in the form of adamelite",
            type = "Beach",
            desc = "The Punai Beach area is another example of a coastal rock formation in Belitong. This geosite is important as representative of I-type granite in the form of adamelite. Another island sits just off the shore called Pandan Island. This location has now become a popular tourist destination",
            plant = "-",
            animal = "-",
            distance = 1000,
            location = "Tanjung Kelumpang, Simpang Pesak, East Belitung Regency",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/7b890094-353a-4227-a08d-628c70a29df1/BLT_19_SPN+%281554%29.JPG?format=1000w",
        ),
        Geosite(
            id = 16,
            name = "Garumedang Tektite",
            summary = "These rocks commonly known as ‘Satam Stones’ or ‘Black Diamonds’ and high pressure & tin",
            type = "The Mining",
            desc = "The tektite is an important geological resource of Belitung - the Belitung name itself comes from a locally found form of tektite, the ‘Billitonite’. These rocks commonly known as ‘Satam Stones’ or ‘Black Diamonds’ are formed by collisions of extraterrestrial objects under high pressure and temperature and contain a significant amount of tin. Thus, the tektite is the reason for Belitong’s rich tin mining history.\n" +
                    "\n" +
                    "The Garumedang Tektite is a designated conservation area; tin mining is prohibited here as to not damage the coastal environment it lies on. The geosite also houses information on the history and culture of tin mining in Belitung. Biodiversity conservation is also practiced here; this area is populated by sea cypress  (Casuarinaequisetifolia) and other flora that have conservation and environmental treatment uses such as lake treatment and bird conservation.",
            plant = "Cemara Laut (Casuarinaequisetifolia)",
            animal = "-",
            distance = 11000,
            location = "DSn Garumedang, Kecamatan Damar",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "",
        ),
        Geosite(
            id = 17,
            name = "Kuale Granite Mangrove Forest",
            summary = "This area a typical mangrove granite rock and offers deep breathing mangrove atmosphere",
            type = "River",
            desc = "Situs itu sendiri merupakan batuan granit bakau yang khas di mana setidaknya 6 spesies pohon bakau dapat ditemukan. Selama abad ke-19, daerah ini dikenal sebagai pelabuhan perdagangan yang menghubungkan sungai ke Laut Natuna di utara. Kawasan ini menawarkan suasana hutan bakau yang masih asri. Pengunjung dapat berjalan kaki sepanjang 650 meter di sepanjang tepi sungai sambil menikmati hutan bakau. \n" +
                    "\n" +
                    "Pelapukan Granit Tanjung Pandan berupa endapan pasir kuarsa/aluvial, berumur Kuarter. Ditemukan juga sejumlah blok granit dari Tanjung Pandan, berumur Trias, 208 hingga 245 ma.",
            plant = "-",
            animal = "-",
            distance = 1100,
            location = "Jl. Penghul, Sijuk, Kabupaten Belitung",
            hours = listOf(
                "Mon-Thu & Sat:\t9AM–4PM",
                "Friday:\t9AM–11AM, 1–4PM",
                "Sunday:\tClosed",
            ),
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558990195742-8AEQYYV6N5Q0KN0EILSI/Kuale-Sijuk-1.jpg?format=500w",
        ),
    )

    fun getAllBiodiversity() = listOf(
        Biodiversity(
            id = 1,
            name = "Moss Hill",
            type = "Hill",
            location = "Limbongan, Kec. Gantung, East Belitung Regency",
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560494357865-JI4N7MAOI1B2IVO99HLC/Martin-Gunung-Lumut-01.jpg?format=1500w",
        ),
        Biodiversity(
            id = 2,
            name = "Belitong Tersier",
            type = "Animal",
            location = "Peramun Hill",
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558800513560-U4GR2ZHB6PFYFMUHE9PT/SLIDER-BG12.jpg?format=750w",
        ),
        Biodiversity(
            id = 3,
            name = "Batu Pulas",
            type = "Granite Rock",
            location = "Dusun Mengguru, Cendil, Kec. Klp. Kampit, Kabupaten Belitung Timur",
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560485122888-WEC7W4U7BRBTOKXBR5BC/Martin-Batu-Pulas-07.jpg?format=1500w",
        ),
        Biodiversity(
            id = 3,
            name = "Peramun Hill Granite Forest",
            type = "Hill",
            location = "Jl. Peramun, Air Selumar, Sijuk, Kabupaten Belitung",
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558975216815-9IOOH4H0AO2KKZ4PNGWQ/peramun4.jpg?format=1000w",
        ),
        Biodiversity(
            id = 4,
            name = "Tristaniopsis Merguensis",
            type = "Plant",
            location = "-",
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560552291389-Y01PGQ7IJKQYQ60RPIZ1/Oki-Cendil-Kulat-Pelawan-2.jpg?format=1000w",
        ),
        Biodiversity(
            id = 5,
            name = "Asian Arowana",
            type = "Animal",
            location = "Tebat Rasau Cenozoic",
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558919810324-P7UUJK9T9UHF51W2958U/asian-arowana.jpg?format=300w",
        ),
        Biodiversity(
            id = 6,
            name = "Tropical Pitcher",
            type = "Plant",
            location = "Moss Hill",
            img = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560494821473-0AFVKPLCVCQIUTZBJK4P/Martin-Gunung-Lumut-04.jpg?format=300w",
        ),
    )

    fun getAllOrder() = listOf(
        Order(
            id = 1,
            geositeName = "Moss Hill",
            geositeImage = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1560494357865-JI4N7MAOI1B2IVO99HLC/Martin-Gunung-Lumut-01.jpg?format=1500w",
            tourGuideName = "Givan Massa Adrian",
            tourGuidePhone = "08123456789",
            bookingDate = "Sat, 5 June 2023 at 08.00 WIB",
            tourDate = "Sun, 6 June 2023 at 10.00 WIB",
            programName = "Geo-Education",
            status = "Pending",
        ),
        Order(
            id = 2,
            geositeName = "Nam Salu Open Pit",
            geositeImage = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558970893738-X3BO2VX1PBE34AHDRVV7/nam-salu-7.jpg?format=1000w",
            tourGuideName = "Fachridan Tio",
            tourGuidePhone = "08123456789",
            bookingDate = "Sat, 8 June 2023 at 08.00 WIB",
            tourDate = "Sun, 12 June 2023 at 10.00 WIB",
            programName = "Research-Programme",
            status = "Completed",
        ),
        Order(
            id = 3,
            geositeName = "Juru Seberang",
            geositeImage = "https://images.squarespace-cdn.com/content/v1/5cdcf79637f3770001708f38/1558849946386-EYT83UVSON1V10TCFIEO/Juru-Seberang-7.jpg?format=500w",
            tourGuideName = "Yolanda Putri",
            tourGuidePhone = "08123456789",
            bookingDate = "Sat, 8 June 2023 at 08.00 WIB",
            tourDate = "Sun, 12 June 2023 at 10.00 WIB",
            programName = "Research-Programme",
            status = "Cancelled",
        ),
    )

    fun getPlant() = Plant(
        id = 1,
        name = "Tropical Pitcher",
        latin = "Nepenthes",
    )
}