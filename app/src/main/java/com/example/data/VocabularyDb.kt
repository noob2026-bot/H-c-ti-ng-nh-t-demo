package com.example.data

data class WordItem(
    val id: String,
    val word: String,
    val kanji: String,
    val meaning: String
)

object VocabularyDb {
    val database: Map<Int, List<WordItem>> = mapOf(
        1 to listOf(
            WordItem("1_1", "わたし", "私", "tôi"),
            WordItem("1_2", "あなた", "あなた", "bạn, anh/chị"),
            WordItem("1_3", "あのひと", "あの人", "người kia, người đó"),
            WordItem("1_4", "せんせい", "先生", "giáo viên"),
            WordItem("1_5", "がくせい", "学生", "học sinh, sinh viên"),
            WordItem("1_6", "かいしゃいん", "会社員", "nhân viên công ty"),
            WordItem("1_7", "いしゃ", "医者", "bác sĩ"),
            WordItem("1_8", "だいがく", "大学", "trường đại học")
        ),
        2 to listOf(
            WordItem("2_1", "これ", "これ", "đây, cái này"),
            WordItem("2_2", "それ", "それ", "đó, cái đó"),
            WordItem("2_3", "ほん", "本", "sách"),
            WordItem("2_4", "じしょ", "辞書", "từ điển"),
            WordItem("2_5", "ざっし", "雑誌", "tạp chí"),
            WordItem("2_6", "しんぶん", "新聞", "báo"),
            WordItem("2_7", "かぎ", "かぎ", "chìa khóa"),
            WordItem("2_8", "とけい", "時計", "đồng hồ")
        ),
        3 to listOf(
            WordItem("3_1", "ここ", "ここ", "chỗ này, đây"),
            WordItem("3_2", "どこ", "どこ", "đâu, chỗ nào"),
            WordItem("3_3", "きょうしつ", "教室", "phòng học"),
            WordItem("3_4", "しょくどう", "食堂", "nhà ăn"),
            WordItem("3_5", "じむしょ", "事務所", "văn phòng"),
            WordItem("3_6", "かいぎしつ", "会議室", "phòng họp"),
            WordItem("3_7", "うけつけ", "受付", "quầy tiếp tân"),
            WordItem("3_8", "へや", "部屋", "phòng")
        ),
        4 to listOf(
            WordItem("4_1", "おきます", "起きます", "thức dậy"),
            WordItem("4_2", "ねます", "寝ます", "ngủ"),
            WordItem("4_3", "はたらきます", "働きます", "làm việc"),
            WordItem("4_4", "やすみます", "休みます", "nghỉ ngơi"),
            WordItem("4_5", "べんきょうします", "勉強します", "học tập"),
            WordItem("4_6", "おわります", "終わります", "kết thúc, hết"),
            WordItem("4_7", "いま", "今", "bây giờ"),
            WordItem("4_8", "きょう", "今日", "hôm nay")
        ),
        5 to listOf(
            WordItem("5_1", "いきます", "行きます", "đi"),
            WordItem("5_2", "きます", "来ます", "đến"),
            WordItem("5_3", "かえります", "帰ります", "về"),
            WordItem("5_4", "がっこう", "学校", "trường học"),
            WordItem("5_5", "えき", "駅", "ga, nhà ga"),
            WordItem("5_6", "ひこうき", "飛行機", "máy bay"),
            WordItem("5_7", "でんしゃ", "電車", "tàu điện"),
            WordItem("5_8", "ともだち", "友達", "bạn bè")
        ),
        6 to listOf(
            WordItem("6_1", "たべます", "食べます", "ăn"),
            WordItem("6_2", "のみます", "飲みます", "uống"),
            WordItem("6_3", "すいます", "吸います", "hút [thuốc]"),
            WordItem("6_4", "みます", "見ます", "xem, nhìn"),
            WordItem("6_5", "ききます", "聞きます", "nghe"),
            WordItem("6_6", "よみます", "読みます", "đọc"),
            WordItem("6_7", "かきます", "書きます", "viết"),
            WordItem("6_8", "かいます", "買います", "mua")
        ),
        7 to listOf(
            WordItem("7_1", "きります", "切ります", "cắt"),
            WordItem("7_2", "おくります", "送ります", "gửi"),
            WordItem("7_3", "あげます", "あげます", "tặng"),
            WordItem("7_4", "もらいます", "もらいます", "nhận"),
            WordItem("7_5", "かします", "貸します", "cho mượn"),
            WordItem("7_6", "かります", "借ります", "mượn"),
            WordItem("7_7", "おしえます", "教えます", "dạy"),
            WordItem("7_8", "ならいます", "習います", "học tập")
        ),
        8 to listOf(
            WordItem("8_1", "きれい", "きれいない", "đẹp, sạch"),
            WordItem("8_2", "しずか", "静か", "yên tĩnh"),
            WordItem("8_3", "ゆうめい", "有名", "nổi tiếng"),
            WordItem("8_4", "げんき", "元気", "khỏe"),
            WordItem("8_5", "おおきい", "大きい", "to, lớn"),
            WordItem("8_6", "ちいさい", "小さい", "nhỏ"),
            WordItem("8_7", "あたらしい", "新しい", "mới"),
            WordItem("8_8", "たかい", "高い", "đắt, cao")
        ),
        9 to listOf(
            WordItem("9_1", "わかります", "分かります", "hiểu, nắm được"),
            WordItem("9_2", "あります", "あります", "có (sở hữu đồ vật)"),
            WordItem("9_3", "すき", "好き", "thích"),
            WordItem("9_4", "きらい", "嫌い", "ghét, không thích"),
            WordItem("9_5", "じょうず", "上手", "giỏi, khéo"),
            WordItem("9_6", "りょうり", "料理", "món ăn, nấu ăn"),
            WordItem("9_7", "おんがく", "音楽", "âm nhạc"),
            WordItem("9_8", "じかん", "時間", "thời gian")
        ),
        10 to listOf(
            WordItem("10_1", "います", "います", "có, ở (người, động vật)"),
            WordItem("10_2", "おとこのひと", "男の人", "người đàn ông"),
            WordItem("10_3", "おんなのひと", "女の人", "người phụ nữ"),
            WordItem("10_4", "いぬ", "犬", "chó"),
            WordItem("10_5", "ねこ", "猫", "mèo"),
            WordItem("10_6", "き", "木", "cây, gỗ"),
            WordItem("10_7", "こうえん", "公園", "công viên"),
            WordItem("10_8", "ほんや", "本屋", "hiệu sách")
        ),
        11 to listOf(
            WordItem("11_1", "ひとつ", "ひとつ", "1 cái (đồ vật)"),
            WordItem("11_2", "ひとり", "一人", "1 người"),
            WordItem("11_3", "ふたり", "二人", "2 người"),
            WordItem("11_4", "りんご", "りんご", "quả táo"),
            WordItem("11_5", "きって", "切手", "tem"),
            WordItem("11_6", "きょうだい", "兄弟", "anh em"),
            WordItem("11_7", "かかります", "かかります", "mất, tốn (thời gian/tiền)"),
            WordItem("11_8", "やすみます", "休みます", "nghỉ ngơi")
        ),
        12 to listOf(
            WordItem("12_1", "かんたん", "簡単", "đơn giản, dễ"),
            WordItem("12_2", "ちかい", "近い", "gần"),
            WordItem("12_3", "とおい", "遠い", "xa"),
            WordItem("12_4", "はやい", "早い", "nhanh, sớm"),
            WordItem("12_5", "おそい", "遅い", "chậm, muộn"),
            WordItem("12_6", "あたたかい", "温かい", "ấm"),
            WordItem("12_7", "すずしい", "涼しい", "mát mẻ"),
            WordItem("12_8", "あまい", "甘い", "ngọt")
        ),
        13 to listOf(
            WordItem("13_1", "あそびます", "遊びます", "chơi"),
            WordItem("13_2", "およぎます", "泳ぎます", "bơi"),
            WordItem("13_3", "むかえます", "迎えます", "đón"),
            WordItem("13_4", "つかれます", "疲れます", "mệt"),
            WordItem("13_5", "ほしい", "欲しい", "muốn có"),
            WordItem("13_6", "ひろい", "広い", "rộng"),
            WordItem("13_7", "せまい", "狭い", "chật, hẹp"),
            WordItem("13_8", "びじゅつ", "美術", "mỹ thuật")
        ),
        14 to listOf(
            WordItem("14_1", "つけます", "つけます", "bật (điện)"),
            WordItem("14_2", "けします", "消します", "tắt (điện)"),
            WordItem("14_3", "あけます", "開けます", "mở (cửa)"),
            WordItem("14_4", "しめます", "閉めます", "đóng (cửa)"),
            WordItem("14_5", "いそぎます", "急ぎます", "vội, gấp"),
            WordItem("14_6", "まちます", "待ちます", "đợi, chờ"),
            WordItem("14_7", "とめます", "止めます", "dừng"),
            WordItem("14_8", "なまえ", "名前", "tên")
        ),
        15 to listOf(
            WordItem("15_1", "たちます", "立ちます", "đứng"),
            WordItem("15_2", "すわります", "座ります", "ngồi"),
            WordItem("15_3", "つかいます", "使います", "dùng, sử dụng"),
            WordItem("15_4", "おきます", "置きます", "đặt, để"),
            WordItem("15_5", "つくります", "作ります", "làm, chế tạo"),
            WordItem("15_6", "うります", "売ります", "bán"),
            WordItem("15_7", "しります", "知ります", "biết"),
            WordItem("15_8", "すみます", "住みます", "sống, ở")
        ),
        16 to listOf(
            WordItem("16_1", "のります", "乗ります", "đi, lên (tàu)"),
            WordItem("16_2", "おります", "降ります", "xuống (tàu)"),
            WordItem("16_3", "のりかえます", "乗り換えます", "chuyển (tàu)"),
            WordItem("16_4", "あびます", "浴びます", "tắm (vòi sen)"),
            WordItem("16_5", "いれます", "入れます", "cho vào"),
            WordItem("16_6", "だします", "出します", "lấy ra"),
            WordItem("16_7", "おします", "押します", "bấm, ấn (nút)"),
            WordItem("16_8", "わかい", "若い", "trẻ")
        ),
        17 to listOf(
            WordItem("17_1", "おぼえます", "覚えます", "nhớ"),
            WordItem("17_2", "わすれます", "忘れます", "quên"),
            WordItem("17_3", "なくします", "なくします", "mất, đánh mất"),
            WordItem("17_4", "はらいます", "払います", "trả tiền"),
            WordItem("17_5", "かえします", "返します", "trả lại"),
            WordItem("17_6", "でかけます", "出かけます", "ra ngoài"),
            WordItem("17_7", "ぬぎます", "脱ぎます", "cởi (áo, giày)"),
            WordItem("17_8", "あぶない", "危ない", "nguy hiểm")
        ),
        18 to listOf(
            WordItem("18_1", "できます", "できます", "có thể"),
            WordItem("18_2", "あらいます", "洗います", "rửa"),
            WordItem("18_3", "ひきます", "弾きます", "chơi (nhạc cụ)"),
            WordItem("18_4", "うたいます", "歌います", "hát"),
            WordItem("18_5", "あつめます", "集めます", "sưu tập"),
            WordItem("18_6", "すてます", "捨てます", "vứt, bỏ đi"),
            WordItem("18_7", "かえます", "換えます", "đổi"),
            WordItem("18_8", "しゅみ", "趣味", "sở thích")
        ),
        19 to listOf(
            WordItem("19_1", "のぼります", "登ります", "leo (núi)"),
            WordItem("19_2", "とまります", "泊まります", "trọ, ở lại"),
            WordItem("19_3", "そうじします", "掃除します", "dọn dẹp"),
            WordItem("19_4", "せんたくします", "洗濯します", "giặt giũ"),
            WordItem("19_5", "なります", "なります", "trở nên"),
            WordItem("19_6", "ねむい", "眠い", "buồn ngủ"),
            WordItem("19_7", "つよい", "強い", "mạnh"),
            WordItem("19_8", "よわい", "弱い", "yếu")
        ),
        20 to listOf(
            WordItem("20_1", "いります", "要ります", "cần"),
            WordItem("20_2", "しらべます", "調べます", "tìm hiểu"),
            WordItem("20_3", "なおします", "直します", "sửa, chữa"),
            WordItem("20_4", "しゅうりします", "修理します", "sửa chữa (máy)"),
            WordItem("20_5", "でんわします", "電話します", "gọi điện thoại"),
            WordItem("20_6", "ことば", "言葉", "từ, tiếng"),
            WordItem("20_7", "ぶっか", "物価", "vật giá"),
            WordItem("20_8", "きもの", "着物", "Kimono")
        ),
        21 to listOf(
            WordItem("21_1", "おmoいます", "思います", "nghĩ"),
            WordItem("21_2", "いいます", "言います", "nói"),
            WordItem("21_3", "たります", "足ります", "đủ"),
            WordItem("21_4", "かちます", "勝ちます", "thắng"),
            WordItem("21_5", "まけます", "負けます", "thua"),
            WordItem("21_6", "やくにたちます", "役に立ちます", "giúp ích"),
            WordItem("21_7", "むだ", "むだ", "vô ích"),
            WordItem("21_8", "ふべん", "不便", "bất tiện")
        ),
        22 to listOf(
            WordItem("22_1", "きます", "着ます", "mặc (áo)"),
            WordItem("22_2", "はきます", "はきます", "mang (giày, quần)"),
            WordItem("22_3", "かぶります", "かぶります", "đội (nón)"),
            WordItem("22_4", "かけます", "掛けます", "đeo (kính)"),
            WordItem("22_5", "うまれます", "生まれます", "sinh ra"),
            WordItem("22_6", "ぼうし", "帽子", "nón, mũ"),
            WordItem("22_7", "めがね", "眼鏡", "kính"),
            WordItem("22_8", "やちん", "家賃", "tiền nhà")
        ),
        23 to listOf(
            WordItem("23_1", "ききます", "聞きます", "hỏi"),
            WordItem("23_2", "まわします", "回します", "vặn (nút)"),
            WordItem("23_3", "ひきます", "引きます", "kéo"),
            WordItem("23_4", "かえます", "変えます", "đổi"),
            WordItem("23_5", "さわります", "触ります", "sờ, chạm"),
            WordItem("23_6", "うごきます", "動きます", "chuyển động"),
            WordItem("23_7", "あるきます", "歩きます", "đi bộ"),
            WordItem("23_8", "わたります", "渡ります", "qua (cầu)")
        ),
        24 to listOf(
            WordItem("24_1", "くれます", "くれます", "cho, tặng (tôi)"),
            WordItem("24_2", "つれていきます", "連れて行きます", "dẫn đi"),
            WordItem("24_3", "つれてきます", "連れて来ます", "dẫn đến"),
            WordItem("24_4", "おくります", "送ります", "đưa đi, tiễn"),
            WordItem("24_5", "しょうかいします", "紹介します", "giới thiệu"),
            WordItem("24_6", "あんないします", "案内します", "hướng dẫn"),
            WordItem("24_7", "せつめいします", "説明します", "giải thích"),
            WordItem("24_8", "じゅんび", "準備", "chuẩn bị")
        ),
        25 to listOf(
            WordItem("25_1", "かんがえます", "考えます", "suy nghĩ"),
            WordItem("25_2", "つきます", "着きます", "đến"),
            WordItem("25_3", "りゅうがくします", "留学します", "du học"),
            WordItem("25_4", "トります", "取ります", "thêm (tuổi)"),
            WordItem("25_5", "いなか", "田舎", "quê"),
            WordItem("25_6", "たいしかん", "大使館", "đại sứ quán"),
            WordItem("25_7", "おく", "億", "100 triệu"),
            WordItem("25_8", "もし", "もし", "nếu")
        ),
        26 to listOf(
            WordItem("26_1", "みます", "見ます/診ます", "xem, khám"),
            WordItem("26_2", "さがします", "探します", "tìm kiếm"),
            WordItem("26_3", "おくれます", "遅れます", "chậm, muộn"),
            WordItem("26_4", "まにあいます", "間に合います", "kịp giờ"),
            WordItem("26_5", "さんかします", "参加します", "tham gia"),
            WordItem("26_6", "もうしこみます", "申し込みます", "đăng ký"),
            WordItem("26_7", "つごうがいい", "都合がいい", "thuận tiện"),
            WordItem("26_8", "きぶんがいい", "気分がいい", "tâm trạng tốt")
        ),
        27 to listOf(
            WordItem("27_1", "かいます", "飼います", "nuôi (động vật)"),
            WordItem("27_2", "たてます", "建てます", "xây dựng"),
            WordItem("27_3", "はしります", "走ります", "chạy"),
            WordItem("27_4", "とります", "取ります", "xin (nghỉ)"),
            WordItem("27_5", "みえます", "見えます", "có thể nhìn thấy"),
            WordItem("27_6", "きこえます", "聞こえます", "có thể nghe thấy"),
            WordItem("27_7", "ひらきます", "開きます", "mở (lớp)"),
            WordItem("27_8", "けしき", "景色", "phong cảnh")
        ),
        28 to listOf(
            WordItem("28_1", "うれます", "売れます", "bán chạy"),
            WordItem("28_2", "おどります", "踊ります", "nhảy múa"),
            WordItem("28_3", "かみます", "かみます", "nhai, cắn"),
            WordItem("28_4", "えらびます", "選びます", "chọn"),
            WordItem("28_5", "かよいます", "通います", "đi (học, làm)"),
            WordItem("28_6", "メモします", "メモします", "ghi chép"),
            WordItem("28_7", "まじめ", "まじめ", "nghiêm túc"),
            WordItem("28_8", "ねっしん", "熱心", "nhiệt tình")
        ),
        29 to listOf(
            WordItem("29_1", "あきます", "開きます", "mở (tự động)"),
            WordItem("29_2", "しまります", "閉まります", "đóng (tự động)"),
            WordItem("29_3", "つきます", "付きます", "bật (điện)"),
            WordItem("29_4", "きえます", "消えます", "tắt (điện)"),
            WordItem("29_5", "こみます", "込みます", "đông đúc"),
            WordItem("29_6", "こわれます", "壊れます", "hỏng"),
            WordItem("29_7", "われます", "割れます", "vỡ"),
            WordItem("29_8", "おとします", "落とします", "làm rơi")
        ),
        30 to listOf(
            WordItem("30_1", "はります", "貼ります", "dán"),
            WordItem("30_2", "かけます", "掛けます", "treo"),
            WordItem("30_3", "かざります", "飾ります", "trang trí"),
            WordItem("30_4", "ならべます", "並べます", "xếp thành hàng"),
            WordItem("30_5", "うえます", "植えます", "trồng (cây)"),
            WordItem("30_6", "もどします", "戻します", "đưa về chỗ cũ"),
            WordItem("30_7", "まとめます", "まとめます", "thu thập, tóm tắt"),
            WordItem("30_8", "かたづけます", "片づけます", "dọn dẹp")
        ),
        31 to listOf(
            WordItem("31_1", "はじまります", "始まります", "bắt đầu"),
            WordItem("31_2", "つづけます", "続けます", "tiếp tục"),
            WordItem("31_3", "みつけます", "見つけます", "tìm thấy"),
            WordItem("31_4", "うけます", "受けます", "thi"),
            WordItem("31_5", "にゅうがくします", "入学します", "nhập học"),
            WordItem("31_6", "そつぎょうします", "卒業します", "tốt nghiệp"),
            WordItem("31_7", "きゅうけいします", "休憩します", "giải lao"),
            WordItem("31_8", "れんきゅう", "連休", "ngày nghỉ liền nhau")
        ),
        32 to listOf(
            WordItem("32_1", "うんどうします", "運動します", "vận động"),
            WordItem("32_2", "せいこうします", "成功します", "thành công"),
            WordItem("32_3", "しっぱいします", "失敗します", "thất bại"),
            WordItem("32_4", "ごうかくします", "合格します", "đỗ, qua kì thi"),
            WordItem("32_5", "もどります", "戻ります", "quay lại"),
            WordItem("32_6", "やみます", "止みます", "tạnh (mưa)"),
            WordItem("32_7", "はれます", "晴れます", "nắng"),
            WordItem("32_8", "くもります", "曇ります", "nhiều mây")
        ),
        33 to listOf(
            WordItem("33_1", "にげます", "逃げます", "chạy trốn"),
            WordItem("33_2", "さわぎます", "騒ぎます", "làm ồn"),
            WordItem("33_3", "あきらめます", "諦めます", "từ bỏ"),
            WordItem("33_4", "なげます", "投げます", "ném"),
            WordItem("33_5", "ままおります", "守ります", "bảo vệ, tuân thủ"),
            WordItem("33_6", "あげます", "上げます", "nâng lên"),
            WordItem("33_7", "さげます", "下げます", "hạ xuống"),
            WordItem("33_8", "つたえます", "伝えます", "truyền đạt")
        ),
        34 to listOf(
            WordItem("34_1", "みがきます", "磨きます", "đánh (răng), mài"),
            WordItem("34_2", "くみたてます", "組み立てます", "lắp ráp"),
            WordItem("34_3", "おります", "折ります", "gập, bẻ"),
            WordItem("34_4", "きがつきまs", "気が付きます", "nhận thấy"),
            WordItem("34_5", "つけます", "付けます", "chấm (nước tương)"),
            WordItem("34_6", "みつかります", "見つかります", "được tìm thấy"),
            WordItem("34_7", "しつもんします", "質問します", "hỏi"),
            WordItem("34_8", "ほそい", "細い", "thon, nhỏ")
        ),
        35 to listOf(
            WordItem("35_1", "さきます", "咲きます", "nở (hoa)"),
            WordItem("35_2", "かわります", "変わります", "thay đổi"),
            WordItem("35_3", "こまります", "困ります", "rắc rối"),
            WordItem("35_4", "つけます", "付けます", "vẽ dấu"),
            WordItem("35_5", "ひろいます", "拾います", "nhặt"),
            WordItem("35_6", "かかります", "かかります", "có điện thoại"),
            WordItem("35_7", "らく", "楽", "thoải mái"),
            WordItem("35_8", "ただしい", "正しい", "đúng, chính xác")
        ),
        36 to listOf(
            WordItem("36_1", "とどきます", "届きます", "được gửi đến"),
            WordItem("36_2", "でます", "出ます", "tham gia"),
            WordItem("36_3", "うちます", "打ちます", "đánh (chữ)"),
            WordItem("36_4", "ちょきんします", "貯金します", "tiết kiệm tiền"),
            WordItem("36_5", "ふとります", "太ります", "béo lên"),
            WordItem("36_6", "やせます", "やせます", "gầy đi"),
            WordItem("36_7", "すぎます", "過ぎます", "quá (giờ)"),
            WordItem("36_8", "なれます", "慣れます", "quen")
        ),
        37 to listOf(
            WordItem("37_1", "ほめます", "褒めます", "khen"),
            WordItem("37_2", "しかります", "叱ります", "mắng"),
            WordItem("37_3", "さそいます", "誘います", "mời, rủ"),
            WordItem("37_4", "おこします", "起こします", "đánh thức"),
            WordItem("37_5", "しょうたいします", "招待します", "mời"),
            WordItem("37_6", "たのみます", "頼みます", "nhờ"),
            WordItem("37_7", "ちゅういします", "注意します", "chú ý, nhắc nhở"),
            WordItem("37_8", "とります", "取ります", "ăn trộm, lấy")
        ),
        38 to listOf(
            WordItem("38_1", "そだてます", "育てます", "nuôi, trồng"),
            WordItem("38_2", "はこびます", "運びます", "vận chuyển"),
            WordItem("38_3", "なくなります", "亡くなります", "mất, qua đời"),
            WordItem("38_4", "にゅういんします", "入院します", "nhập viện"),
            WordItem("38_5", "たいいんします", "退院します", "xuất viện"),
            WordItem("38_6", "いれます", "入れます", "bật (công tắc)"),
            WordItem("38_7", "きります", "切ります", "tắt (công tắc)"),
            WordItem("38_8", "かけます", "掛けます", "khóa (chìa)")
        ),
        39 to listOf(
            WordItem("39_1", "こたえます", "答えます", "trả lời"),
            WordItem("39_2", "たおれます", "倒れます", "đổ"),
            WordItem("39_3", "やけます", "焼けます", "cháy"),
            WordItem("39_4", "とおります", "通ります", "đi qua"),
            WordItem("39_5", "しにます", "死にます", "chết"),
            WordItem("39_6", "びっくりします", "びっくりします", "ngạc nhiên"),
            WordItem("39_7", "がっかりします", "がっかりします", "thất vọng"),
            WordItem("39_8", "あんしんします", "安心します", "yên tâm")
        ),
        40 to listOf(
            WordItem("40_1", "かぞえます", "数えます", "đếm"),
            WordItem("40_2", "はかります", "測ります", "đo, cân"),
            WordItem("40_3", "たしかめます", "確かめます", "xác nhận"),
            WordItem("40_4", "あいます", "合います", "vừa, hợp"),
            WordItem("40_5", "しゅっぱつします", "出発します", "xuất phát"),
            WordItem("40_6", "とうちゃくします", "到着します", "đến nơi"),
            WordItem("40_7", "よいます", "酔います", "say"),
            WordItem("40_8", "きけん", "危険", "nguy hiểm")
        ),
        41 to listOf(
            WordItem("41_1", "いただきます", "頂きます", "nhận (kính ngữ)"),
            WordItem("41_2", "くださいます", "下さいます", "cho, tặng (kính ngữ)"),
            WordItem("41_3", "やります", "やります", "cho (người dưới)"),
            WordItem("41_4", "よびます", "呼びます", "mời, gọi"),
            WordItem("41_5", "とりかえます", "取り替えます", "đổi, thay"),
            WordItem("41_6", "しんせつにします", "親切にします", "đối xử tử tế"),
            WordItem("41_7", "かわいい", "可愛い", "dễ thương"),
            WordItem("41_8", "おいわい", "お祝い", "quà mừng")
        ),
        42 to listOf(
            WordItem("42_1", "つつみます", "包みます", "gói, bọc"),
            WordItem("42_2", "わかします", "沸かします", "đun sôi"),
            WordItem("42_3", "まぜます", "混ぜます", "trộn"),
            WordItem("42_4", "けいさんします", "計算します", "tính toán"),
            WordItem("42_5", "あつい", "厚い", "dày"),
            WordItem("42_6", "うすい", "薄い", "mỏng"),
            WordItem("42_7", "べんごし", "弁護士", "luật sư"),
            WordItem("42_8", "おんがくか", "音楽家", "nhạc sĩ")
        ),
        43 to listOf(
            WordItem("43_1", "ふえます", "増えます", "tăng lên"),
            WordItem("43_2", "へります", "減ります", "giảm xuống"),
            WordItem("43_3", "あがります", "上がります", "tăng (giá)"),
            WordItem("43_4", "さがります", "下がります", "giảm (giá)"),
            WordItem("43_5", "きれます", "切れます", "đứt"),
            WordItem("43_6", "とれます", "とれます", "tuột"),
            WordItem("43_7", "おちます", "落ちます", "rơi"),
            WordItem("43_8", "なくなります", "無くなります", "hết, mất")
        ),
        44 to listOf(
            WordItem("44_1", "なきます", "泣きます", "khóc"),
            WordItem("44_2", "わらいます", "笑います", "cười"),
            WordItem("44_3", "かわきます", "乾きます", "khô"),
            WordItem("44_4", "ぬれます", "濡れます", "ướt"),
            WordItem("44_5", "すべります", "滑ります", "trượt"),
            WordItem("44_6", "おきます", "起きます", "xảy ra"),
            WordItem("44_7", "ちょうせつします", "調節します", "điều chỉnh"),
            WordItem("44_8", "あんぜん", "安全", "an toàn")
        ),
        45 to listOf(
            WordItem("45_1", "あやまります", "謝ります", "xin lỗi"),
            WordItem("45_2", "あいます", "遭います", "gặp (tai nạn)"),
            WordItem("45_3", "しんじます", "信じます", "tin tưởng"),
            WordItem("45_4", "よういします", "用意します", "chuẩn bị"),
            WordItem("45_5", "キャンセルします", "キャンセルします", "hủy"),
            WordItem("45_6", "うまくいきます", "うまくいきます", "thuận lợi"),
            WordItem("45_7", "ほしょうしょ", "保証書", "giấy bảo hành"),
            WordItem("45_8", "りょうしゅうしょ", "領収書", "hóa đơn")
        ),
        46 to listOf(
            WordItem("46_1", "やきます", "焼きます", "nướng"),
            WordItem("46_2", "わたします", "渡します", "trao"),
            WordItem("46_3", "かえってきます", "帰ってきます", "quay về"),
            WordItem("46_4", "でます", "出ます", "rời, xuất phát"),
            WordItem("46_5", "るす", "留守", "vắng nhà"),
            WordItem("46_6", "たくはいびん", "宅配便", "chuyển phát tận nhà"),
            WordItem("46_7", "げんいん", "原因", "nguyên nhân"),
            WordItem("46_8", "ちゅうしゃ", "注射", "tiêm")
        ),
        47 to listOf(
            WordItem("47_1", "あつまります", "集まります", "tập trung"),
            WordItem("47_2", "わかれます", "別れます", "chia tay, phân chia"),
            WordItem("47_3", "ながいきします", "長生きします", "sống thọ"),
            WordItem("47_4", "します", "します", "có [mùi/vị/tiếng]"),
            WordItem("47_5", "さします", "さします", "che (ô)"),
            WordItem("47_6", "ひどい", "ひどい", "tồi tệ"),
            WordItem("47_7", "こわい", "怖い", "sợ, đáng sợ"),
            WordItem("47_8", "てんきよほう", "天気予報", "dự báo thời tiết")
        ),
        48 to listOf(
            WordItem("48_1", "おろします", "降ろします", "cho xuống"),
            WordItem("48_2", "とどけます", "届けます", "gửi đến"),
            WordItem("48_3", "せわをします", "世話をします", "chăm sóc"),
            WordItem("48_4", "ろくおんします", "録音します", "ghi âm"),
            WordItem("48_5", "いや", "嫌", "không thích"),
            WordItem("48_6", "きびしい", "厳しい", "nghiêm khắc"),
            WordItem("48_7", "じゅく", "塾", "cơ sở học thêm"),
            WordItem("48_8", "せいと", "生徒", "học trò")
        ),
        49 to listOf(
            WordItem("49_1", "めしあがります", "召し上がります", "ăn, uống (Tôn kính ngữ)"),
            WordItem("49_2", "いらっしゃいます", "いらっしゃいます", "ở, đi, đến (Tôn kính ngữ)"),
            WordItem("49_3", "おっしゃいます", "おっしゃいます", "nói (Tôn kính ngữ)"),
            WordItem("49_4", "なさいます", "なさいます", "làm (Tôn kính ngữ)"),
            WordItem("49_5", "ごらんになります", "ご覧になります", "xem (Tôn kính ngữ)"),
            WordItem("49_6", "ごぞんじです", "ご存じです", "biết (Tôn kính ngữ)"),
            WordItem("49_7", "あいさつ", "あいさつ", "chào hỏi"),
            WordItem("49_8", "りょかん", "旅館", "nhà trọ kiểu Nhật")
        ),
        50 to listOf(
            WordItem("50_1", "まいります", "参ります", "đi, đến (Khiêm nhường ngữ)"),
            WordItem("50_2", "おりまs", "おります", "ở (Khiêm nhường ngữ)"),
            WordItem("50_3", "いただきます", "頂きます", "ăn, uống, nhận (Khiêm nhường ngữ)"),
            WordItem("50_4", "もうします", "申します", "nói (Khiêm nhường ngữ)"),
            WordItem("50_5", "いたします", "いたします", "làm (Khiêm nhường ngữ)"),
            WordItem("50_6", "はいけんします", "拝見します", "xem (Khiêm nhường ngữ)"),
            WordItem("50_7", "ぞんじます", "存じます", "biết (Khiêm nhường ngữ)"),
            WordItem("50_8", "うかがいます", "伺います", "hỏi, nghe, đến thăm (Khiêm nhường ngữ)")
        )
    )

    val allVocab: List<WordItem> = database.values.flatten()
}
