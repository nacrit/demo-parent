package com.example.demo.design_pattern.a_head_first_design_patterns.builder;

import java.util.ArrayList;

//不同的媒体形式
class Media extends ArrayList {}
//不同的媒体形式:杂志
class Magazine extends Media {}
//不同的媒体形式:书
class Book extends Media {}
//不同的媒体形式:网站
class WebSite extends Media {}
