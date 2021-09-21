---
title: COMP207 - Lectures
layout: article
aside:
 toc: true
sidebar:
 nav: comp207
---
{% for post in site.posts %}
{% if post.tags contains "Lectures" and post.tags contains "COMP207" %}
# [{{post.title}}]({{site.baseurl}}{{post.url}})
{::nomarkdown}
{{post.content}}
{:/nomarkdown}
{% endif %}
{% endfor %}
