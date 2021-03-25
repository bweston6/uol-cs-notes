---
title: COMP116 - Tutorials
layout: article
aside:
 toc: true
sidebar:
 nav: comp116
---
{% for post in site.posts %}
{% if post.tags contains "Tutorials" and post.tags contains "COMP116" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
