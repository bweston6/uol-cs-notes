---
title: COMP211
layout: article
aside:
 toc: true
sidebar:
 nav: comp211
---
{% for post in site.posts %}
{% if post.tags contains "COMP211" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
