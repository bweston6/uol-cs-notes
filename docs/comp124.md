---
title: COMP124
layout: article
aside:
 toc: true
sidebar:
 nav: comp124
---
{% for post in site.posts %}
{% if post.tags contains "COMP124" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{{post.content}}
{% endif %}
{% endfor %}
