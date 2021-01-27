---
title: COMP107 - Seminars
layout: article
aside:
 toc: true
sidebar:
 nav: comp107
---
{% for post in site.posts %}
{% if post.tags contains "Seminars" and post.categories contains "COMP107" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{{post.content}}
{% endif %}
{% endfor %}
