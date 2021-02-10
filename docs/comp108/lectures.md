---
title: COMP108 - Lectures
layout: article
aside:
 toc: true
sidebar:
 nav: comp108
---
{% for post in site.posts %}
{% if post.tags contains "Lectures" and post.tags contains "COMP108" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{{post.content}}
{% endif %}
{% endfor %}
