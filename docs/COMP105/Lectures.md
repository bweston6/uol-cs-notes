---
title: COMP105 - Lectures
layout: article
aside:
 toc: true
sidebar:
 nav: comp105
---
{% for post in site.posts %}
{% if post.tags contains "Lectures" and post.categories contains "COMP105" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{{post.content}}
{% endif %}
{% endfor %}
