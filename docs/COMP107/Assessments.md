---
title: COMP107 - Assessments
layout: article
aside:
 toc: true
sidebar:
 nav: comp107
---
{% for post in site.posts %}
{% if post.tags contains "Assessments" and post.categories contains "COMP107" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{{post.content}}
{% endif %}
{% endfor %}
