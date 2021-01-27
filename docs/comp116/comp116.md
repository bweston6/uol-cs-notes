---
title: COMP116
layout: article
aside:
 toc: true
sidebar:
 nav: comp116
---
{% for post in site.posts %}
{% if post.tags contains "COMP116" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{{post.content}}
{% endif %}
{% endfor %}
