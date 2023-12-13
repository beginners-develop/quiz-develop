-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th12 13, 2023 lúc 04:36 PM
-- Phiên bản máy phục vụ: 10.4.27-MariaDB
-- Phiên bản PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `cee_db`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `admin_acc`
--

CREATE TABLE `admin_acc` (
  `admin_id` int(11) NOT NULL,
  `admin_user` varchar(1000) NOT NULL,
  `admin_pass` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `admin_acc`
--

INSERT INTO `admin_acc` (`admin_id`, `admin_user`, `admin_pass`) VALUES
(1, 'admin@username', 'admin@password'),
(2, 'admin', '123456');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `course_tbl`
--

CREATE TABLE `course_tbl` (
  `cou_id` int(11) NOT NULL,
  `cou_name` varchar(1000) NOT NULL,
  `cou_created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `course_tbl`
--

INSERT INTO `course_tbl` (`cou_id`, `cou_name`, `cou_created`) VALUES
(25, 'Giải tích 1', '2023-12-13 13:38:12'),
(26, 'Hệ cơ sở dữ liệu', '2023-12-13 13:37:49'),
(65, 'Công nghệ phần mềm', '2023-12-13 15:24:00');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `examinee_tbl`
--

CREATE TABLE `examinee_tbl` (
  `exmne_id` int(11) NOT NULL,
  `exmne_fullname` varchar(1000) NOT NULL,
  `exmne_course` varchar(1000) NOT NULL,
  `exmne_gender` varchar(1000) NOT NULL,
  `exmne_birthdate` varchar(1000) NOT NULL,
  `exmne_year_level` varchar(1000) NOT NULL,
  `exmne_email` varchar(1000) NOT NULL,
  `exmne_password` varchar(1000) NOT NULL,
  `exmne_status` varchar(1000) NOT NULL DEFAULT 'active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `examinee_tbl`
--

INSERT INTO `examinee_tbl` (`exmne_id`, `exmne_fullname`, `exmne_course`, `exmne_gender`, `exmne_birthdate`, `exmne_year_level`, `exmne_email`, `exmne_password`, `exmne_status`) VALUES
(4, 'Rogz Nunezsss', '26', 'male', '2019-11-15', 'năm 1', 'rogz.nunez2013@gmail.com', 'rogz', 'active'),
(5, 'Jane Rivera', '25', 'female', '2019-11-14', 'năm 2', 'jane@gmail.com', 'jane', 'active'),
(6, 'Glenn Duerme', '26', 'female', '2019-12-24', 'năm 3', 'glenn@gmail.com', 'glenn', 'active'),
(7, 'Maria Duerme', '26', 'female', '2018-11-25', 'năm 2', 'maria@gmail.com', 'maria', 'active'),
(8, 'Dave Limasac', '26', 'female', '2019-12-21', 'năm 1', 'dave@gmail.com', 'dave', 'active');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `exam_answers`
--

CREATE TABLE `exam_answers` (
  `exans_id` int(11) NOT NULL,
  `axmne_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  `quest_id` int(11) NOT NULL,
  `exans_answer` varchar(1000) NOT NULL,
  `exans_status` varchar(1000) NOT NULL DEFAULT 'new',
  `exans_created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `exam_answers`
--

INSERT INTO `exam_answers` (`exans_id`, `axmne_id`, `exam_id`, `quest_id`, `exans_answer`, `exans_status`, `exans_created`) VALUES
(295, 4, 12, 25, 'Diode, inverted, pointer', 'old', '2019-12-07 02:52:14'),
(296, 4, 12, 16, 'Data Block', 'old', '2019-12-07 02:52:14'),
(297, 6, 12, 18, 'Programmable Logic Controller', 'old', '2019-12-05 12:59:47'),
(298, 6, 12, 9, '1850s', 'old', '2019-12-05 12:59:47'),
(299, 6, 12, 24, '1976', 'old', '2019-12-05 12:59:47'),
(300, 6, 12, 14, 'Operating System', 'old', '2019-12-05 12:59:47'),
(301, 6, 12, 19, 'WAN (Wide Area Network)', 'old', '2019-12-05 12:59:47'),
(302, 6, 11, 28, 'fds', 'new', '2019-12-05 12:04:28'),
(303, 6, 11, 29, 'sd', 'new', '2019-12-05 12:04:28'),
(304, 6, 12, 15, 'David Filo & Jerry Yang', 'new', '2019-12-05 12:59:47'),
(305, 6, 12, 17, 'System file', 'new', '2019-12-05 12:59:47'),
(306, 6, 12, 10, 'Field', 'new', '2019-12-05 12:59:47'),
(307, 6, 12, 9, '1880s', 'new', '2019-12-05 12:59:47'),
(308, 6, 12, 21, 'Temporary file', 'new', '2019-12-05 12:59:47'),
(309, 4, 11, 28, 'q1', 'new', '2019-12-05 13:30:21'),
(310, 4, 11, 29, 'dfg', 'new', '2019-12-05 13:30:21'),
(311, 4, 12, 16, 'Data Block', 'new', '2019-12-07 02:52:14'),
(312, 4, 12, 20, 'Plancks radiation', 'new', '2019-12-07 02:52:14'),
(313, 4, 12, 10, 'Report', 'new', '2019-12-07 02:52:14'),
(314, 4, 12, 24, '1976', 'new', '2019-12-07 02:52:14'),
(315, 4, 12, 9, '1930s', 'new', '2019-12-07 02:52:14'),
(316, 8, 12, 18, 'Programmable Lift Computer', 'old', '2023-12-13 15:19:08'),
(317, 8, 12, 14, 'Operating System', 'old', '2023-12-13 15:19:08'),
(318, 8, 12, 20, 'Einstein oscillation', 'old', '2023-12-13 15:19:08'),
(319, 8, 12, 21, 'Temporary file', 'old', '2023-12-13 15:19:08'),
(320, 8, 12, 25, 'Diode, inverted, pointer', 'old', '2023-12-13 15:19:08'),
(321, 8, 24, 33, '690', 'old', '2023-12-13 15:02:09'),
(322, 8, 24, 34, '223', 'old', '2023-12-13 15:02:09'),
(323, 8, 24, 32, '300 con vịt', 'old', '2023-12-13 15:02:09'),
(324, 8, 24, 35, '3', 'old', '2023-12-13 15:02:09'),
(325, 8, 24, 31, '7', 'old', '2023-12-13 15:02:09'),
(326, 8, 13, 36, '2', 'old', '2023-12-13 15:04:54'),
(327, 8, 11, 28, 'asd', 'new', '2023-12-13 14:38:19'),
(328, 8, 11, 30, 'fgh', 'new', '2023-12-13 14:38:19'),
(329, 8, 11, 29, 'dfg', 'new', '2023-12-13 14:38:19'),
(330, 8, 24, 33, '690', 'new', '2023-12-13 15:02:09'),
(331, 8, 24, 34, '223', 'new', '2023-12-13 15:02:09'),
(332, 8, 24, 35, '3', 'new', '2023-12-13 15:02:09'),
(333, 8, 24, 31, '7', 'new', '2023-12-13 15:02:09'),
(334, 8, 24, 32, '300 con vịt', 'new', '2023-12-13 15:02:09'),
(335, 8, 13, 36, '2', 'new', '2023-12-13 15:04:54'),
(336, 8, 12, 19, 'URL (Universal Resource Locator)', 'new', '2023-12-13 15:19:08'),
(337, 8, 12, 22, 'Local Area Network', 'new', '2023-12-13 15:19:08'),
(338, 8, 12, 24, '1984', 'new', '2023-12-13 15:19:08'),
(339, 8, 12, 16, 'Driver Boot', 'new', '2023-12-13 15:19:08'),
(340, 8, 12, 20, 'Plancks radiation', 'new', '2023-12-13 15:19:08'),
(341, 8, 12, 14, 'Operating System', 'new', '2023-12-13 15:19:08'),
(342, 8, 12, 18, 'Programmable Logic Controller', 'new', '2023-12-13 15:19:08'),
(343, 8, 12, 15, 'Vint Cerf & Robert Kahn', 'new', '2023-12-13 15:19:08'),
(344, 8, 12, 9, '1930s', 'new', '2023-12-13 15:19:08'),
(345, 8, 12, 10, 'Record', 'new', '2023-12-13 15:19:08'),
(346, 8, 12, 21, 'Temporary file', 'new', '2023-12-13 15:19:08'),
(347, 8, 12, 25, 'Diode, inverted, pointer', 'new', '2023-12-13 15:19:08'),
(348, 8, 12, 17, 'Hypertext related file', 'new', '2023-12-13 15:19:08');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `exam_attempt`
--

CREATE TABLE `exam_attempt` (
  `examat_id` int(11) NOT NULL,
  `exmne_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  `examat_status` varchar(1000) NOT NULL DEFAULT 'used'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `exam_attempt`
--

INSERT INTO `exam_attempt` (`examat_id`, `exmne_id`, `exam_id`, `examat_status`) VALUES
(51, 6, 12, 'used'),
(52, 4, 11, 'used'),
(53, 4, 12, 'used');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `exam_question_tbl`
--

CREATE TABLE `exam_question_tbl` (
  `eqt_id` int(11) NOT NULL,
  `exam_id` int(11) NOT NULL,
  `exam_question` varchar(1000) NOT NULL,
  `exam_ch1` varchar(1000) NOT NULL,
  `exam_ch2` varchar(1000) NOT NULL,
  `exam_ch3` varchar(1000) NOT NULL,
  `exam_ch4` varchar(1000) NOT NULL,
  `exam_answer` varchar(1000) NOT NULL,
  `exam_status` varchar(1000) NOT NULL DEFAULT 'active'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `exam_question_tbl`
--

INSERT INTO `exam_question_tbl` (`eqt_id`, `exam_id`, `exam_question`, `exam_ch1`, `exam_ch2`, `exam_ch3`, `exam_ch4`, `exam_answer`, `exam_status`) VALUES
(9, 12, 'In which decade was the American Institute of Electrical Engineers (AIEE) founded?', '1850s', '1880s', '1930s', '1950s', '1880s', 'active'),
(10, 12, 'What is part of a database that holds only one type of information?', 'Report', 'Field', 'Record', 'File', 'Field', 'active'),
(14, 12, 'OS computer abbreviation usually means ?', 'Order of Significance', 'Open Software', 'Operating System', 'Optical Sensor', 'Operating System', 'active'),
(15, 12, 'Who developed Yahoo?', 'Dennis Ritchie & Ken Thompson', 'David Filo & Jerry Yang', 'Vint Cerf & Robert Kahn', 'Steve Case & Jeff Bezos', 'David Filo & Jerry Yang', 'active'),
(16, 12, 'DB computer abbreviation usually means ?', 'Database', 'Double Byte', 'Data Block', 'Driver Boot', 'Database', 'active'),
(17, 12, '.INI extension refers usually to what kind of file?', 'Image file', 'System file', 'Hypertext related file', 'Image Color Matching Profile file', 'System file', 'active'),
(18, 12, 'What does the term PLC stand for?', 'Programmable Lift Computer', 'Program List Control', 'Programmable Logic Controller', 'Piezo Lamp Connector', 'Programmable Logic Controller', 'active'),
(19, 12, 'What do we call a network whose elements may be separated by some distance? It usually involves two or more small networks and dedicated high-speed telephone lines.', 'URL (Universal Resource Locator)', 'LAN (Local Area Network)', 'WAN (Wide Area Network)', 'World Wide Web', 'WAN (Wide Area Network)', 'active'),
(20, 12, 'After the first photons of light are produced, which process is responsible for amplification of the light?', 'Blackbody radiation', 'Stimulated emission', 'Plancks radiation', 'Einstein oscillation', 'Stimulated emission', 'active'),
(21, 12, '.TMP extension refers usually to what kind of file?', 'Compressed Archive file', 'Image file', 'Temporary file', 'Audio file', 'Temporary file', 'active'),
(22, 12, 'What do we call a collection of two or more computers that are located within a limited distance of each other and that are connected to each other directly or indirectly?', 'Inernet', 'Interanet', 'Local Area Network', 'Wide Area Network', 'Local Area Network', 'active'),
(24, 12, '	 In what year was the \"@\" chosen for its use in e-mail addresses?', '1976', '1972', '1980', '1984', '1972', 'active'),
(25, 12, 'What are three types of lasers?', 'Gas, metal vapor, rock', 'Pointer, diode, CD', 'Diode, inverted, pointer', 'Gas, solid state, diode', 'Gas, solid state, diode', 'active'),
(27, 15, 'asdasd', 'dsf', 'sd', 'yui', 'sdf', 'yui', 'active'),
(28, 11, 'Question 1: ẹbdwdb  3wd  rfee  è f fgg  èwwg gưg g', 'q1', 'asd', 'fds', 'ytu', 'q1', 'active'),
(29, 11, 'Question 2: ndsn ệc ice èw fcf ffw9jcwfc ẹcece êcf', 'asd', 'sd', 'q2', 'dfg', 'q2', 'active'),
(30, 11, 'Question 3: dsbduebeckncwncwakncweacujewc', 'sd', 'q3', 'asd', 'fgh', 'q3', 'active'),
(31, 24, '2 + 5 = ?', '5', '6', '7', '8', '7', 'active'),
(32, 24, 'Nhà bạn Hà nuôi một đàn vịt. Sau khi mẹ bạn Hà bán đi 120 con vịt thì còn lại 180 con vịt. Hỏi lúc đầu nhà bạn Hà nuôi bao nhiêu con vịt?', '60 con vịt', '300 con vịt', '2100 con vịt', '200 con vịt', '300 con vịt', 'active'),
(33, 24, 'Biết x – 115 = 575. Giá trị của x là bao nhiêu?', '460', '690', '680', '700', '690', 'active'),
(34, 24, '100 + 123 = ?', '333', '223', '333', '123', '223', 'active'),
(35, 24, '1 + 2 = ?', '1', '2', '3', '4', '3', 'active'),
(36, 13, '1+1=?', '1', '2', '3', '4', '2', 'active'),
(37, 13, '2 + 2 = ?', '2', '3', '4', '5', '4', 'active');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `exam_tbl`
--

CREATE TABLE `exam_tbl` (
  `ex_id` int(11) NOT NULL,
  `cou_id` int(11) NOT NULL,
  `ex_title` varchar(1000) NOT NULL,
  `ex_time_limit` varchar(1000) NOT NULL,
  `ex_questlimit_display` int(11) NOT NULL,
  `ex_description` varchar(1000) NOT NULL,
  `ex_created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `exam_tbl`
--

INSERT INTO `exam_tbl` (`ex_id`, `cou_id`, `ex_title`, `ex_time_limit`, `ex_questlimit_display`, `ex_description`, `ex_created`) VALUES
(11, 26, 'kiểm tra thí nghiệm', '1', 3, 'thực hành tuần 1', '2023-12-13 13:55:30'),
(12, 26, 'Kiểm tra tiếng anh', '1', 13, 'kiểm tra số 1', '2023-12-13 13:55:20'),
(13, 26, 'GK database', '5', 2, ' kiểm tra giữa kì hk 231', '2023-12-13 15:31:12'),
(24, 26, 'Toán lớp 5', '10', 5, 'hs lớp 5', '2023-12-13 13:54:24');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `feedbacks_tbl`
--

CREATE TABLE `feedbacks_tbl` (
  `fb_id` int(11) NOT NULL,
  `exmne_id` int(11) NOT NULL,
  `fb_exmne_as` varchar(1000) NOT NULL,
  `fb_feedbacks` varchar(1000) NOT NULL,
  `fb_date` varchar(1000) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `feedbacks_tbl`
--

INSERT INTO `feedbacks_tbl` (`fb_id`, `exmne_id`, `fb_exmne_as`, `fb_feedbacks`, `fb_date`) VALUES
(4, 6, 'Glenn Duerme', 'Gwapa kay Miss Pam', 'December 05, 2019'),
(5, 6, 'Anonymous', 'Lageh, idol na nako!', 'December 05, 2019'),
(6, 4, 'Rogz Nunezsss', 'Yes', 'December 08, 2019'),
(7, 4, '', '', 'December 08, 2019'),
(8, 4, '', '', 'December 08, 2019'),
(9, 8, 'Anonymous', 'dfsdf', 'January 05, 2020');

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `admin_acc`
--
ALTER TABLE `admin_acc`
  ADD PRIMARY KEY (`admin_id`);

--
-- Chỉ mục cho bảng `course_tbl`
--
ALTER TABLE `course_tbl`
  ADD PRIMARY KEY (`cou_id`);

--
-- Chỉ mục cho bảng `examinee_tbl`
--
ALTER TABLE `examinee_tbl`
  ADD PRIMARY KEY (`exmne_id`);

--
-- Chỉ mục cho bảng `exam_answers`
--
ALTER TABLE `exam_answers`
  ADD PRIMARY KEY (`exans_id`);

--
-- Chỉ mục cho bảng `exam_attempt`
--
ALTER TABLE `exam_attempt`
  ADD PRIMARY KEY (`examat_id`);

--
-- Chỉ mục cho bảng `exam_question_tbl`
--
ALTER TABLE `exam_question_tbl`
  ADD PRIMARY KEY (`eqt_id`);

--
-- Chỉ mục cho bảng `exam_tbl`
--
ALTER TABLE `exam_tbl`
  ADD PRIMARY KEY (`ex_id`);

--
-- Chỉ mục cho bảng `feedbacks_tbl`
--
ALTER TABLE `feedbacks_tbl`
  ADD PRIMARY KEY (`fb_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `admin_acc`
--
ALTER TABLE `admin_acc`
  MODIFY `admin_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT cho bảng `course_tbl`
--
ALTER TABLE `course_tbl`
  MODIFY `cou_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=67;

--
-- AUTO_INCREMENT cho bảng `examinee_tbl`
--
ALTER TABLE `examinee_tbl`
  MODIFY `exmne_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT cho bảng `exam_answers`
--
ALTER TABLE `exam_answers`
  MODIFY `exans_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=349;

--
-- AUTO_INCREMENT cho bảng `exam_attempt`
--
ALTER TABLE `exam_attempt`
  MODIFY `examat_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT cho bảng `exam_question_tbl`
--
ALTER TABLE `exam_question_tbl`
  MODIFY `eqt_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=38;

--
-- AUTO_INCREMENT cho bảng `exam_tbl`
--
ALTER TABLE `exam_tbl`
  MODIFY `ex_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT cho bảng `feedbacks_tbl`
--
ALTER TABLE `feedbacks_tbl`
  MODIFY `fb_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
