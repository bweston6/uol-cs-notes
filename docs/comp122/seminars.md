---
title: COMP122 - Seminars
layout: article
aside:
 toc: true
sidebar:
 nav: comp122
---
{% for post in site.posts %}
{% if post.tags contains "Seminars" and post.tags contains "COMP122" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{{post.content}}
{% endif %}
{% endfor %}
