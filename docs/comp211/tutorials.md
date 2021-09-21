---
title: COMP211 - Tutorials
layout: article
aside:
 toc: true
sidebar:
 nav: comp211
---
{% for post in site.posts %}
{% if post.tags contains "Tutorials" and post.tags contains "COMP211" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
