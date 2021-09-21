---
title: COMP207
layout: article
aside:
 toc: true
sidebar:
 nav: comp207
---
{% for post in site.posts %}
{% if post.tags contains "COMP207" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
